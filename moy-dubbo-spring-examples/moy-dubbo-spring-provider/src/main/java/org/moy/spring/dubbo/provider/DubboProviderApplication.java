package org.moy.spring.dubbo.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: [程序入口 注册中心]</p>
 * Created on 2018/11/20
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:dubbo/spring*.xml"})
@RestController
public class DubboProviderApplication {

    @Value("${dubbo.application.name}")
    private String serviceName;

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

    @RequestMapping("/")
    public String index() {
        return String.format("%s is ok!", serviceName);
    }

}
