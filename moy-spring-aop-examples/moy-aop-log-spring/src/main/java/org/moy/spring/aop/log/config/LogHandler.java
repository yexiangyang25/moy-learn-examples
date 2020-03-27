package org.moy.spring.aop.log.config;


/**
 * <p>Description: [处理 日志]</p>
 * Created on 2020/03/25
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public interface LogHandler {


    /**
     * 处理
     *
     * @param appLog
     */
    void handle(AppLog appLog);
}
