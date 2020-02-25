package org.moy.spring.dubbo.consumer;

import org.moy.spring.dubbo.api.HelloWorldService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * <p>Description: [程序入口 注册中心]</p>
 * Created on 2018/11/20
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
@SpringBootApplication
@RestController
@ImportResource(locations = {"classpath:dubbo/spring-dubbo.xml", "classpath:dubbo/spring-dubbo-consumer.xml"})
public class DubboConsumerApplication {


    @Resource
    private HelloWorldService helloWorldService;

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }

    @RequestMapping("/")
    public String sayHelloWorld() {
        return helloWorldService.sayHelloWorld();
    }

    @RequestMapping("/hi/{name}")
    public String sayHelloWorld(@PathVariable("name") String name) {
        return helloWorldService.sayHi(name);
    }
}
