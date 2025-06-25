package com.photo.judge.common.annotation;

import java.lang.annotation.*;

/**
 * 描述注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Documented
@Inherited
public @interface Desc {
    String value() default "";
}
