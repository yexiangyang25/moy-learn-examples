package org.moy.spring.aop.log.config.util;


import org.apache.commons.lang3.reflect.MethodUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * <p>Description: [反射 帮助类]</p>
 * Created on 2018/7/5
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public class ReflectHelper {

    private ReflectHelper() {
    }

    /**
     * 获取类上指定注解
     *
     * @param joinPoint
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getAnnotationOfMethod(JoinPoint joinPoint, Class<T> clazz) {
        T annotation = null;
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            try {
                Method subMethod = joinPoint.getTarget().getClass().getMethod(method.getName(), method.getParameterTypes());
                annotation = MethodUtils.getAnnotation(subMethod, clazz, true, false);
                return null != annotation ? annotation : MethodUtils.getAnnotation(method, clazz, true, false);
            } catch (NoSuchMethodException e) {
                // do nothing
            }
        }
        return null;
    }

    /**
     * 转化堆栈字符串
     *
     * @param throwable
     * @return
     */
    public static String getStackTraceString(Throwable throwable) {
        if (null == throwable) {
            return null;
        }
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }


}
