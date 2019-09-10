package com.wt.overflow.datasource;

import java.lang.annotation.*;

/**
 * 暂时废弃
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface DateSource {
    String value() default "datasource1";
}
