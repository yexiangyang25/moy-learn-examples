package org.moy.spring.aop.log.config;


import org.aspectj.lang.JoinPoint;

/**
 * <p>Description: [处理 日志]</p>
 * Created on 2020/03/25
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public interface MethodLogHandler {

    /**
     * 方法执行之前
     *
     * @param joinPoint    反射信息
     * @param appLogConfig 自定义配置
     * @return
     */
    AppLog buildBefore(JoinPoint joinPoint, AppLogConfig appLogConfig);


    /**
     * 方法出现异常
     *
     * @param appLog       方法执行之前获取的信息
     * @param joinPoint    反射信息
     * @param e            执行方法自身出现的异常
     * @param appLogConfig 自定义配置
     * @return 方法返回值
     * @throws Throwable 如果不能处理,应该抛出方法自身出现的异常,不能将方法自身出现的异常丢掉
     */
    Object  buildAfterThrowing(AppLog appLog, JoinPoint joinPoint, Throwable e, AppLogConfig appLogConfig) throws Throwable;


    /**
     * 方法执行之后,总是会执行(出现异常也会执行)
     *
     * @param appLog       方法执行之前获取的信息
     * @param result       方法执行结果
     * @param appLogConfig 自定义配置
     */
    void buildAfter(AppLog appLog, Object result, AppLogConfig appLogConfig);
}
