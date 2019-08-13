package com.yahoo.zyfx.yuanweiming.annotation;

import java.lang.annotation.*;

/**
 * @program: spring_mvc
 * @description:
 * @author: chinasie-29@crc.com.hk
 * @create: 2019-08-13 11:43
 */
@Documented//javadoc
@Target(ElementType.TYPE)//作用在类上
@Retention(RetentionPolicy.RUNTIME)//限制Annotation的生命周期，我这里自定义的注解显然需要运行时保留
public @interface Controller {
    
    /**
    *@Description: Controller名称
    *@Param: 
    *@return: 
    *@Author: chinasie-29@crc.com.hk
    *@date: 2019/8/13
    */
    String value();
}
