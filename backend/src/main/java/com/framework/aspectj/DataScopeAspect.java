package com.framework.aspectj;

import java.lang.reflect.Method;

import com.common.utils.spring.SpringUtils;
import com.framework.security.LoginUser;
import com.framework.web.domain.BaseEntity;
import com.project.system.domain.SysRole;
import com.project.system.domain.SysUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.common.utils.ServletUtils;
import com.common.utils.StringUtils;
import com.framework.aspectj.lang.annotation.DataScope;
import com.framework.security.service.TokenService;

/**
 * 数据过滤处理
 */
@Aspect
@Component
public class DataScopeAspect
{
    public static final String DATA_SCOPE_ALL = "1";

    public static final String DATA_SCOPE_CUSTOM = "2";

    public static final String DATA_SCOPE_DEPT = "3";

    public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";

    public static final String DATA_SCOPE_SELF = "5";

    @Pointcut("@annotation(com.framework.aspectj.lang.annotation.DataScope)")
    public void dataScopePointCut()
    {
    }

    @Before("dataScopePointCut()")
    public void doBefore(JoinPoint point) throws Throwable
    {
        handleDataScope(point);
    }

    protected void handleDataScope(final JoinPoint joinPoint)
    {
        DataScope controllerDataScope = getAnnotationLog(joinPoint);
        if (controllerDataScope == null)
        {
            return;
        }
        LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
        SysUser currentUser = loginUser.getUser();
        if (currentUser != null)
        {
            if (!currentUser.isAdmin())
            {
                dataScopeFilter(joinPoint, currentUser, controllerDataScope.deptAlias(),
                        controllerDataScope.userAlias());
            }
        }
    }

    public static void dataScopeFilter(JoinPoint joinPoint, SysUser user, String deptAlias, String userAlias)
    {
        StringBuilder sqlString = new StringBuilder();

        for (SysRole role : user.getRoles())
        {
            String dataScope = role.getDataScope();
            if (DATA_SCOPE_ALL.equals(dataScope))
            {
                sqlString = new StringBuilder();
                break;
            }
            else if (DATA_SCOPE_SELF.equals(dataScope))
            {
                if (StringUtils.isNotBlank(userAlias))
                {
                    sqlString.append(StringUtils.format(" OR {}.user_id = {} ", userAlias, user.getUserId()));
                }
                else
                {
                    sqlString.append(" OR 1=0 ");
                }
            }
        }

        if (StringUtils.isNotBlank(sqlString.toString()))
        {
            BaseEntity baseEntity = (BaseEntity) joinPoint.getArgs()[0];
            baseEntity.setDataScope(" AND (" + sqlString.substring(4) + ")");
        }
    }

    private DataScope getAnnotationLog(JoinPoint joinPoint)
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(DataScope.class);
        }
        return null;
    }
}
