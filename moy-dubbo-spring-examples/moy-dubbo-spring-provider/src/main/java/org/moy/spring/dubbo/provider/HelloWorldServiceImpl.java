package org.moy.spring.dubbo.provider;

import org.moy.spring.dubbo.api.HelloWorldService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>Description: [程序入口 注册中心]</p>
 * Created on 2018/11/20
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
@Service
public class HelloWorldServiceImpl implements HelloWorldService {

    @Override
    public String sayHi(String name) {
        return String.format("Hi, %s", name);
    }

    @Override
    public String sayHelloWorld() {
        return "Hello World!";
    }
}
