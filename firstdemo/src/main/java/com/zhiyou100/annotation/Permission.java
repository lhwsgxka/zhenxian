package com.zhiyou100.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/***
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
    String[] role();
}
