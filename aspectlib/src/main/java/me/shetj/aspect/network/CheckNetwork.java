package me.shetj.aspect.network;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *网络判断，isNeedNet 是否有网才执行
 * @author shetj
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckNetwork {
    boolean isNeedNet() default true; //是否需要网络才能执行
}