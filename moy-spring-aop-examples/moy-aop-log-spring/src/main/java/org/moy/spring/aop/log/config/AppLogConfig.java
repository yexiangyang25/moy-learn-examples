package org.moy.spring.aop.log.config;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;


/**
 * <p>Description: [自定义日志配置]</p>
 * Created on 2020/03/25
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {METHOD})
public @interface AppLogConfig {

    /**
     * 如果记录方法入参，则将方法入参格式化为JSON;如果记录方法返回值,则将记录方法返回值格式化为JSON
     *
     * @return
     */
    boolean jsonFormat() default true;

    /**
     * 记录方法入参
     *
     * @return
     */
    boolean argsPrint() default true;

    /**
     * 记录方法返回值
     *
     * @return
     */
    boolean resultPrint() default true;

    /**
     * 指定出现异常堆栈转化成结果,前提方法返回值需要实现接口
     *
     * @see ExceptionToResult
     */
    boolean formatException() default true;

    /**
     * 出现异常,如果是http请求，设置响应码500
     *
     * @return
     */
    boolean setInternalServerError() default true;


    /**
     * 自定义类名称描述,不指定则默认使用执行类的全类名
     *
     * @return
     */
    String className() default AppLog.DEFAULT_EMPTY_VALUE;

    /**
     * 自定义方法名称描述,不指定则默认使用执行方法的名称
     *
     * @return
     */
    String classMethodName() default AppLog.DEFAULT_EMPTY_VALUE;


    /**
     * 如果是http接口,不指定则默认使用访问的URI
     *
     * @return
     */
    String uri() default AppLog.DEFAULT_EMPTY_VALUE;

    /**
     * 类型
     *
     * @return
     */
    String type() default AppLog.DEFAULT_EMPTY_VALUE;
}
