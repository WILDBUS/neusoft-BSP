package com.common.utils.sql;

import com.common.utils.StringUtils;

/**
 * sql操作工具类
 */
public class SqlUtil
{

    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,]+";

    public static String escapeOrderBySql(String value)
    {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value))
        {
            return StringUtils.EMPTY;
        }
        return value;
    }
    public static boolean isValidOrderBySql(String value)
    {
        return value.matches(SQL_PATTERN);
    }
}
