package org.moy.spring.aop.log.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.moy.spring.aop.log.config.util.DefaultMethodLogHandler;
import org.moy.spring.aop.log.config.util.ReflectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * <p>Description: [切面 配置拦截]</p>
 * Created on 2019/5/20
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
@Aspect
public class AopConfig {

    private static final Logger logger = LoggerFactory.getLogger(AopConfig.class);
    @Value("${api.timeout:3000}")
    private long timeout = 3000;
    @Value("${api.timeoutWarn:true}")
    private boolean timeoutWarn = true;
    /**
     * 默认输入日志，可自定义
     */
    private MethodLogHandler methodLogHandler = new DefaultMethodLogHandler();

    public void setMethodLogHandler(MethodLogHandler methodLogHandler) {
        this.methodLogHandler = methodLogHandler;
    }

    /**
     * 表达式配置
     */
    @Pointcut("execution (public * org.moy.spring.aop.controller.*.*(..))")
    private void expressionPointCut() {
    }

    /**
     * 注解配置
     */
    @Pointcut("@annotation(org.moy.spring.aop.log.config.AppLogConfig)")
    public void annotationPointCut() {
    }


    @Around(value = "annotationPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        AppLogConfig config = ReflectHelper.getAnnotationOfMethod(point, AppLogConfig.class);
        final AppLog appLog = methodLogHandler.buildBefore(point, config);
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable ex) {
            logger.error("[异常方法签名]: " + point.getSignature(), ex);
            result = methodLogHandler.buildAfterThrowing(appLog, point, ex, config);
        } finally {
            methodLogHandler.buildAfter(appLog, result, config);
            if (timeoutWarn && appLog.getExecTime() > timeout) {
                logger.warn("[方法签名]: {} [执行耗时]: {}ms", point.getSignature(), appLog.getExecTime());
            }
        }
        return result;
    }
}
