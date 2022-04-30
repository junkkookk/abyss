package com.van.common.anno;

import java.lang.annotation.*;

/**
 * @author wan
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebLog {

    String value();

    boolean noUser() default false;

    String operator() default "";

}
