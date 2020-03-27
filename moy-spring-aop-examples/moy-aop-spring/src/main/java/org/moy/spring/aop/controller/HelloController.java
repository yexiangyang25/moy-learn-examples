package org.moy.spring.aop.controller;

import org.moy.spring.aop.dto.Demo1;
import org.moy.spring.aop.dto.Demo2;
import org.moy.spring.aop.dto.HttpResult;
import org.moy.spring.aop.log.config.AppLogConfig;
import org.moy.spring.aop.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>Description: [测试 接口]</p>
 * Created on 2019/5/20
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
@RestController
public class HelloController {

    @Resource
    private TestService testService;

    @AppLogConfig
    @RequestMapping("/")
    public void helloWorld() {
        testService.sayHi("helloWorld");
    }


    @AppLogConfig
    @RequestMapping("/hi")
    public HttpResult<String> hi() {
        String world = testService.sayHi("world");
        return HttpResult.success(world);
    }


    @AppLogConfig
    @RequestMapping("/demo1")
    public HttpResult<Demo1> demo1() {
        Demo1 demo1 = testService.getDemo1(1L);
        return HttpResult.success(demo1);
    }

    @AppLogConfig
    @RequestMapping("/demo2")
    public HttpResult<Demo2> demo2() {
        Demo2 demo2 = testService.getDemo2(2L);
        return HttpResult.success(demo2);
    }


    @AppLogConfig(formatException = false)
    @RequestMapping("/demo21")
    public HttpResult<Demo2> demo21() {
        Demo2 demo2 = testService.getDemo2(21L);
        return HttpResult.success(demo2);
    }
}
