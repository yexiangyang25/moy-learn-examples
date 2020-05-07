package org.moy.spring.aop.log.config.util;

import org.aspectj.lang.JoinPoint;
import org.moy.spring.aop.log.config.AppLog;
import org.moy.spring.aop.log.config.AppLogConfig;
import org.moy.spring.aop.log.config.MethodLogHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * <p>Description: [默认处理 日志]</p>
 * Created on 2020/03/25
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public class DefaultMethodLogHandler implements MethodLogHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMethodLogHandler.class);

    @Override
    public AppLog buildBefore(JoinPoint joinPoint, AppLogConfig appLogConfig) {
        AppLog appLog = new AppLog();
        appLog.setBeginTime(System.currentTimeMillis());
        appLog.setSignatureName(joinPoint.getSignature().toString());
        // 没有自定义注解配置 默认
        if (null == appLogConfig) {
            appLog.setArgs(joinPoint.getArgs());
            appLog.setArgsJsonString(JsonHelper.toJsonString(joinPoint.getArgs()));
        } else {
            if (appLogConfig.argsPrint()) {
                appLog.setArgs(joinPoint.getArgs());
                if (appLogConfig.jsonFormat()) {
                    appLog.setArgsJsonString(JsonHelper.toJsonString(joinPoint.getArgs()));
                }
            }
            // 自定义配置则使用配置项
            appLog.setClassName(appLogConfig.className());
            appLog.setClassMethodName(appLogConfig.classMethodName());
            appLog.setUri(appLogConfig.uri());
            appLog.setType(appLogConfig.type());
        }
        // 默认指定
        if (Objects.equals(appLog.getType(), AppLog.DEFAULT_EMPTY_VALUE)) {
            appLog.setType(AppLog.DEFAULT_TYPE);
        }
        if (Objects.equals(appLog.getUri(), AppLog.DEFAULT_EMPTY_VALUE)) {
            appLog.setUri(ServletHelper.getRequestUri());
        }
        if (Objects.equals(appLog.getClassName(), AppLog.DEFAULT_EMPTY_VALUE)) {
            appLog.setClassName(joinPoint.getSignature().getDeclaringType().getName());
        }
        if (Objects.equals(appLog.getClassMethodName(), AppLog.DEFAULT_EMPTY_VALUE)) {
            appLog.setClassMethodName(joinPoint.getSignature().getName());
        }
        return appLog;
    }

    @Override
    public Object buildAfterThrowing(AppLog appLog, JoinPoint joinPoint, Throwable e, AppLogConfig appLogConfig) throws Throwable {
        appLog.setHasException(true);
        appLog.setExceptionStackTraceString(ReflectHelper.getStackTraceString(e));
        // 出现异常,如果是http请求，设置响应码500
        if (null == appLogConfig || appLogConfig.setInternalServerError()) {
            ServletHelper.setInternalServerError();
        }
        // 如果返回值实现了格式化异常接口,则使用接口返回结果替换异常
        if (AppLog.needFormatException(joinPoint, appLogConfig)) {
            Object result = AppLog.formatExceptionToResult(joinPoint, e);
            appLog.setResult(result);
            return result;
        } else {
            throw e;
        }
    }


    @Override
    public void buildAfter(AppLog appLog, Object result, AppLogConfig appLogConfig) {
        appLog.setEndTime(System.currentTimeMillis());
        appLog.setExecTime(appLog.getEndTime() - appLog.getBeginTime());
        if (null == appLogConfig) {
            appLog.setResult(result);
            appLog.setResultJsonString(JsonHelper.toJsonString(result));
        } else {
            if (appLogConfig.resultPrint()) {
                appLog.setResult(result);
                if (appLogConfig.jsonFormat()) {
                    appLog.setResultJsonString(JsonHelper.toJsonString(result));
                }
            }
        }
        // 收集日志
        LOGGER.info(JsonHelper.toJsonString(appLog));
    }
}
