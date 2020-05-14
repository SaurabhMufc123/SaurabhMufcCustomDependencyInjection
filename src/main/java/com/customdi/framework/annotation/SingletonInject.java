package com.customdi.framework.annotation;

// Custom annotation on constructor to indicate to the framework to inject the singleton implementation.
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ CONSTRUCTOR, FIELD })
@Retention(RUNTIME)
@Documented
public @interface SingletonInject {

}
