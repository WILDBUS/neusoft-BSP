package com.framework.interceptor.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;

import com.common.filter.RepeatedlyRequestWrapper;
import com.common.utils.StringUtils;
import com.common.utils.http.HttpHelper;
import com.framework.interceptor.RepeatSubmitInterceptor;
import com.framework.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;

/**
 * 判断请求url和数据是否和上一次相同，
 * 如果和上次相同，则是重复提交表单。 有效时间为10秒内。
 */
@Component
public class SameUrlDataInterceptor extends RepeatSubmitInterceptor
{
    public final String REPEAT_PARAMS = "repeatParams";

    public final String REPEAT_TIME = "repeatTime";

    public final String CACHE_REPEAT_KEY = "repeatData";

    @Autowired
    private RedisCache redisCache;

    private int intervalTime = 10;

    public void setIntervalTime(int intervalTime)
    {
        this.intervalTime = intervalTime;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean isRepeatSubmit(HttpServletRequest request)
    {
        RepeatedlyRequestWrapper repeatedlyRequest = (RepeatedlyRequestWrapper) request;
        String nowParams = HttpHelper.getBodyString(repeatedlyRequest);

        if (StringUtils.isEmpty(nowParams))
        {
            nowParams = JSONObject.toJSONString(request.getParameterMap());
        }
        Map<String, Object> nowDataMap = new HashMap<String, Object>();
        nowDataMap.put(REPEAT_PARAMS, nowParams);
        nowDataMap.put(REPEAT_TIME, System.currentTimeMillis());

        String url = request.getRequestURI();

        Object sessionObj = redisCache.getCacheObject(CACHE_REPEAT_KEY);
        if (sessionObj != null)
        {
            Map<String, Object> sessionMap = (Map<String, Object>) sessionObj;
            if (sessionMap.containsKey(url))
            {
                Map<String, Object> preDataMap = (Map<String, Object>) sessionMap.get(url);
                if (compareParams(nowDataMap, preDataMap) && compareTime(nowDataMap, preDataMap))
                {
                    return true;
                }
            }
        }
        Map<String, Object> cacheMap = new HashMap<String, Object>();
        cacheMap.put(url, nowDataMap);
        redisCache.setCacheObject(CACHE_REPEAT_KEY, cacheMap, intervalTime, TimeUnit.SECONDS);
        return false;
    }

    private boolean compareParams(Map<String, Object> nowMap, Map<String, Object> preMap)
    {
        String nowParams = (String) nowMap.get(REPEAT_PARAMS);
        String preParams = (String) preMap.get(REPEAT_PARAMS);
        return nowParams.equals(preParams);
    }

    private boolean compareTime(Map<String, Object> nowMap, Map<String, Object> preMap)
    {
        long time1 = (Long) nowMap.get(REPEAT_TIME);
        long time2 = (Long) preMap.get(REPEAT_TIME);
        if ((time1 - time2) < (this.intervalTime * 1000))
        {
            return true;
        }
        return false;
    }
}
