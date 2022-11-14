package com.xunyunedu.interceptor;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: yhc
 * @Date: 2020/9/19 11:48
 * @Description:在Controller的方法上使用此注解，该方法在映射时会检查用户是否登录，未登录返回401错误
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
}
