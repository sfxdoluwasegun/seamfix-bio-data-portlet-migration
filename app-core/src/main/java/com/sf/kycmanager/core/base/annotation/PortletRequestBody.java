package com.sf.kycmanager.core.base.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PortletRequestBody {
    String DEFAULT = "";

    String value() default DEFAULT;
}
