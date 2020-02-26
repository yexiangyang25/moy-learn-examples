package org.moy.spring.amqp.server;

import org.moy.spring.amqp.server.config.MqConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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
public class AmqpServerApplication {
    private static Logger LOG = LoggerFactory.getLogger(AmqpServerApplication.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/")
    public void send() {
        String routingKey = MqConstants.MOY_QUEUE;
        String message = getMessage();
        LOG.info("routingKey:{} message:{}", routingKey, message);
        amqpTemplate.convertAndSend(routingKey, message);
    }

    @GetMapping("/direct/a")
    public void sendDirectA() {
        String exchange = MqConstants.MOY_DIRECT_EXCHANGE;
        String routingKey = MqConstants.MOY_DIRECT_EXCHANGE_ROUTING_KEY_A;
        String message = getMessage();

        LOG.info("exchange:{} routingKey:{} message:{}", exchange, routingKey, message);
        amqpTemplate.convertAndSend(exchange, routingKey, message);
    }

    @GetMapping("/direct/b")
    public void sendDirectB() {
        String exchange = MqConstants.MOY_DIRECT_EXCHANGE;
        String routingKey = MqConstants.MOY_DIRECT_EXCHANGE_ROUTING_KEY_B;
        String message = getMessage();

        LOG.info("exchange:{} routingKey:{} message:{}", exchange, routingKey, message);
        amqpTemplate.convertAndSend(exchange, routingKey, message);
    }

    @GetMapping("/fanout")
    public void sendFanout() {
        String exchange = MqConstants.MOY_FANOUT_EXCHANGE;
        String routingKey = null;
        String message = getMessage();
        LOG.info("exchange:{} routingKey:{} message:{}", exchange, routingKey, message);
        amqpTemplate.convertAndSend(exchange, routingKey, message);
    }

    private String getMessage() {
        return String.format("id=%s threadName=%s threadId=%s",
                UUID.randomUUID().toString(),
                Thread.currentThread().getName(),
                Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        SpringApplication.run(AmqpServerApplication.class, args);
    }
}
