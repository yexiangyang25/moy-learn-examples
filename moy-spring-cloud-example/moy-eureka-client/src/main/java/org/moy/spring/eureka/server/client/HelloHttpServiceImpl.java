package org.moy.spring.eureka.server.client;

import org.moy.spring.common.pojo.Demo;
import org.moy.spring.common.service.HelloHttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Description: [HTTP接口 服务端]</p>
 * Created on 2018/11/20
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
@RestController
public class HelloHttpServiceImpl implements HelloHttpService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public String index() {
        List<String> services = discoveryClient.getServices();
        String description = discoveryClient.description();
        String result = description + " : " + services.toString();
        LOG.info(result);
        return result;
    }

    @Override
    public Demo demo(@RequestHeader("header") String header,
                     @RequestParam("param") String param,
                     @RequestBody Demo demo) {
        LOG.info("header={} param={} demo{}", header, param, demo);
        return demo;
    }
}
