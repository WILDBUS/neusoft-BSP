package com.framework.aspectj.lang.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.framework.aspectj.lang.enums.BusinessType;
import com.framework.aspectj.lang.enums.OperatorType;

/**
 * 自定义操作日志记录注解
 *
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log
{
    public String title() default "";

    public BusinessType businessType() default BusinessType.OTHER;

    public OperatorType operatorType() default OperatorType.MANAGE;

    public boolean isSaveRequestData() default true;
}
