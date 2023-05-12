package com.clearblade.cloud.iot.v1.utils;

import java.lang.annotation.*;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface AutoValue {
    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.TYPE, ElementType.METHOD})
    public @interface CopyAnnotations {
        Class<? extends Annotation>[] exclude() default {};
    }

    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.TYPE})
    public @interface Builder {
    }
}