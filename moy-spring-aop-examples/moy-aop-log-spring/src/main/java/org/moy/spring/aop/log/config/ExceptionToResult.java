package org.moy.spring.aop.log.config;

/**
 * <p>Description: [根据异常转化指定结果]</p>
 * Created on 2020/03/25
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public interface ExceptionToResult {

    /**
     * 出现异常转化结果
     *
     * @param e
     * @return
     */
    Object format(Throwable e);
}
