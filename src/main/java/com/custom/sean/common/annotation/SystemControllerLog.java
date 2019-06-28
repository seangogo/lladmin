package com.custom.sean.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sean on 2018/2/1.
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemControllerLog {
    /**
     * 描述业务操作 例:Xxx管理-执行Xxx操作
     * @return
     */
    String description() default "";
}