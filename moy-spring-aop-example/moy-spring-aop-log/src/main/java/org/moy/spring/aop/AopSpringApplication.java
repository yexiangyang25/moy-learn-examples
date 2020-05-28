package org.moy.spring.aop;

import org.moy.spring.aop.log.config.AopConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: [程序入口 注册中心]</p>
 * Created on 2020/03/25
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
@SpringBootApplication
public class AopSpringApplication {

    @Bean
    public AopConfig aopConfig() {
        return new AopConfig();
    }

    public static void main(String[] args) {
        SpringApplication.run(AopSpringApplication.class, args);
    }
}
