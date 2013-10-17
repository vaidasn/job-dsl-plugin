package javaposse.jobdsl.plugin;

import javaposse.jobdsl.dsl.helpers.Context;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface DslMethod {
    Class<? extends Context> context();
}
