package org.moy.spring.common.service;

import org.moy.spring.common.pojo.Demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: [默认服务降级默认实现]</p>
 * Created on 2018/12/04
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public class HelloHttpServiceDefaultFallbackImpl implements HelloHttpService {

    private static final Logger LOG = LoggerFactory.getLogger(HelloHttpServiceDefaultFallbackImpl.class);

    @Override
    public String index() {
        LOG.error("服务熔断!!");
        return "";
    }

    @Override
    public Demo demo(String header, String param, Demo demo) {
        LOG.error("服务熔断!!");
        return new Demo();
    }
}
