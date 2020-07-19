package com.framework.aspectj.lang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义导出Excel数据注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel
{
    public String name() default "";

    public String dateFormat() default "";

    public String readConverterExp() default "";

    public ColumnType cellType() default ColumnType.STRING;

    public double height() default 14;

    public double width() default 16;

    public String suffix() default "";

    public String defaultValue() default "";

    public String prompt() default "";

    public String[] combo() default {};

    public boolean isExport() default true;

    public String targetAttr() default "";

    Type type() default Type.ALL;

    public enum Type
    {
        ALL(0), EXPORT(1), IMPORT(2);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }

    public enum ColumnType
    {
        NUMERIC(0), STRING(1);
        private final int value;

        ColumnType(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }
}