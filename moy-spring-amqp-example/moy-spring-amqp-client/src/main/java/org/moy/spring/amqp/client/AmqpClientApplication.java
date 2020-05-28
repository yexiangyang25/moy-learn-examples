package org.moy.spring.amqp.client;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Description: [程序入口 服务提供者]</p>
 * Created on 2018/11/20
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
@SpringBootApplication
public class AmqpClientApplication {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public static void main(String[] args) {
        SpringApplication.run(AmqpClientApplication.class, args);
    }
}
