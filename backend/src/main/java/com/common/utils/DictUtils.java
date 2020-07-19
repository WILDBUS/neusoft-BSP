package com.common.utils;

import java.util.Collection;
import java.util.List;

import com.common.constant.Constants;
import com.common.utils.spring.SpringUtils;
import com.framework.redis.RedisCache;
import com.project.system.domain.SysDictData;

/**
 * 字典工具类
 */
public class DictUtils
{

    public static void setDictCache(String key, List<SysDictData> dictDatas)
    {
        SpringUtils.getBean(RedisCache.class).setCacheObject(getCacheKey(key), dictDatas);
    }

    public static List<SysDictData> getDictCache(String key)
    {
        Object cacheObj = SpringUtils.getBean(RedisCache.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj))
        {
            List<SysDictData> DictDatas = StringUtils.cast(cacheObj);
            return DictDatas;
        }
        return null;
    }

    public static void clearDictCache()
    {
        Collection<String> keys = SpringUtils.getBean(RedisCache.class).keys(Constants.SYS_DICT_KEY + "*");
        SpringUtils.getBean(RedisCache.class).deleteObject(keys);
    }

    public static String getCacheKey(String configKey)
    {
        return Constants.SYS_DICT_KEY + configKey;
    }
}
