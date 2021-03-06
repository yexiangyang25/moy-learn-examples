package org.moy.spring.aop.log.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.moy.spring.aop.log.config.util.ServletHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * <p>Description: [Log 日志信息]</p>
 * Created on 2019/5/20
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public class AppLog implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(AppLog.class);
    public static final String DEFAULT_EMPTY_VALUE = "";
    public static final String DEFAULT_TYPE = "default";
    /**
     * 类型
     */
    private String type;
    /**
     * uri
     */
    private String uri;

    /**
     * 开始时间
     */
    private Long beginTime;
    /**
     * 结束时间
     */
    private Long endTime;
    /**
     * 类名
     */
    private String className;
    /**
     * 类方法名
     */
    private String classMethodName;
    /**
     * 方法入参
     */
    private Object[] args;

    /**
     * JSON格式化方法入参
     */
    private String argsJsonString;
    /**
     * 方法返回值
     */
    private Object result;

    /**
     * JSON格式化方法返回值
     */
    private String resultJsonString;

    /**
     * 执行时间
     */
    private Long execTime;
    /**
     * 全签名
     */
    private String signatureName;

    /**
     * 是否异常
     */
    private Boolean hasException = false;

    /**
     * 异常堆栈字符串
     */
    private String exceptionStackTraceString;

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Boolean getHasException() {
        return hasException;
    }

    public void setHasException(Boolean hasException) {
        this.hasException = hasException;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassMethodName() {
        return classMethodName;
    }

    public void setClassMethodName(String classMethodName) {
        this.classMethodName = classMethodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Long getExecTime() {
        return execTime;
    }

    public void setExecTime(Long execTime) {
        this.execTime = execTime;
    }

    public String getSignatureName() {
        return signatureName;
    }

    public void setSignatureName(String signatureName) {
        this.signatureName = signatureName;
    }

    public String getExceptionStackTraceString() {
        return exceptionStackTraceString;
    }

    public void setExceptionStackTraceString(String exceptionStackTraceString) {
        this.exceptionStackTraceString = exceptionStackTraceString;
    }

    public String getArgsJsonString() {
        return argsJsonString;
    }

    public void setArgsJsonString(String argsJsonString) {
        this.argsJsonString = argsJsonString;
    }

    public String getResultJsonString() {
        return resultJsonString;
    }

    public void setResultJsonString(String resultJsonString) {
        this.resultJsonString = resultJsonString;
    }


    /**
     * 检查异常转化开关是否开启
     *
     * @param joinPoint
     * @param appLogConfig
     * @return
     */
    public static boolean needFormatException(JoinPoint joinPoint, AppLogConfig appLogConfig) {
        // 1.开关不配置 或者设置配置项为true
        if (null == appLogConfig || appLogConfig.formatException()) {
            Signature signature = joinPoint.getSignature();
            if (signature instanceof MethodSignature) {
                MethodSignature methodSignature = (MethodSignature) signature;
                Class returnType = methodSignature.getReturnType();
                // 2.返回值类型实现指定接口 并且有无参构造器
                if (ExceptionToResult.class.isAssignableFrom(returnType)) {
                    return true;
                } else {
                    logger.error("方法返回值:{} 没有实现指定接口:{} 无法进行异常转化!", returnType, ExceptionToResult.class);
                    return false;
                }
            }
        }
        logger.debug("方法签名:{} 没有配置异常转化", joinPoint.getSignature());
        return false;
    }


    /**
     * 则根据异常转化失败结果,转化失败上抛原有异常
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    public static Object formatExceptionToResult(JoinPoint joinPoint, Throwable throwable) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class returnType = signature.getReturnType();
        try {
            if (ExceptionToResult.class.isAssignableFrom(returnType)) {
                Object o = returnType.newInstance();
                if (o instanceof ExceptionToResult) {
                    ExceptionToResult exceptionToResult = (ExceptionToResult) o;
                    return exceptionToResult.format(throwable);
                } else {
                    printInfo(signature);
                    throw throwable;
                }
            } else {
                printInfo(signature);
                throw throwable;
            }
        } catch (Throwable e) {
            logger.error("[异常转化结果]失败,上抛出原本异常", e);
            printInfo(signature);
            throw throwable;
        }
    }

    private static void printInfo(MethodSignature signature) {
        logger.error("[异常转化结果]无法转化,方法签名:{},返回值类型:{},需要满足以下前提条件:{} {}"
                , signature, signature.getReturnType()
                , "1.[方法返回值类型]需要实现接口 " + ExceptionToResult.class.getName()
                , "2.[方法返回值类型]需要提供无参构造器");
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }
}
