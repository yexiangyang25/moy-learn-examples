package org.moy.spring.es;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@RestController
public class EsSpringApplication {
    private static Logger LOG = LoggerFactory.getLogger(EsSpringApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EsSpringApplication.class, args);
    }
}
