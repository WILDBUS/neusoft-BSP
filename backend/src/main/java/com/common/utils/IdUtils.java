package com.common.utils;

import com.common.core.lang.UUID;

/**
 * ID生成器工具类
 */
public class IdUtils
{
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }
    public static String simpleUUID()
    {
        return UUID.randomUUID().toString(true);
    }
    public static String fastUUID()
    {
        return UUID.fastUUID().toString();
    }
    public static String fastSimpleUUID()
    {
        return UUID.fastUUID().toString(true);
    }
}
