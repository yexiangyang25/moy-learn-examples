package org.moy.spring.aop.log.config.util;

import org.moy.spring.aop.log.config.AppLog;
import org.moy.spring.aop.log.config.LogHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: [默认处理 日志]</p>
 * Created on 2020/03/25
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public class DefaultLogHandler implements LogHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultLogHandler.class);

    @Override
    public void handle(AppLog appLog) {
        // 收集日志
        LOGGER.info(JsonHelper.toJsonString(appLog));
    }
}
