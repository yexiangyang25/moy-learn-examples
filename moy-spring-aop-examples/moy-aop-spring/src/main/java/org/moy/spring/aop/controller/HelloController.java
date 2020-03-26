package org.moy.spring.aop.controller;

import org.moy.spring.aop.log.config.AppLogConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @AppLogConfig
    @RequestMapping("/hi")
    public String hi() {
        if (true) {
            int i = 1 / 0;
        }
        return "hi";
    }


    @AppLogConfig
    @RequestMapping("/demo1")
    public Demo1 demo1() {
        if (true) {
            int i = 1 / 0;
        }
        return new Demo1();
    }

    @AppLogConfig
    @RequestMapping("/demo2")
    public Demo2 demo2() {
        return getDemo2();
    }

    private Demo2 getDemo2() {
        if (true) {
            int i = 1 / 0;
        }
        return new Demo2(true);
    }

    @AppLogConfig(needFormatExceptionToResult = false)
    @RequestMapping("/demo21")
    public Demo2 demo21() {
        return getDemo2();
    }
}
