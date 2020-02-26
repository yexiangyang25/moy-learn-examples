package org.moy.spring.amqp.server.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: [amqp配置]</p>
 * Created on 2018/12/09
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
@Configuration
public class RabbitmqDirectExchangeConfig {

    /**
     * 直接点对点模式
     *
     * @return
     */
    @Bean
    public DirectExchange moyDirectExchange() {
        return new DirectExchange(MqConstants.MOY_DIRECT_EXCHANGE);
    }

    @Bean
    public Queue moyDirectExchangeQueueA() {
        return new Queue(MqConstants.MOY_DIRECT_EXCHANGE_QUEUE_A);
    }

    @Bean
    public Binding bindingMoyDirectExchangeA() {
        return BindingBuilder.bind(moyDirectExchangeQueueA())
                .to(moyDirectExchange()).with(MqConstants.MOY_DIRECT_EXCHANGE_ROUTING_KEY_A);
    }

    @Bean
    public Queue moyDirectExchangeQueueB() {
        return new Queue(MqConstants.MOY_DIRECT_EXCHANGE_QUEUE_B);
    }

    @Bean
    public Binding bindingMoyDirectExchangeB() {
        return BindingBuilder.bind(moyDirectExchangeQueueB())
                .to(moyDirectExchange()).with(MqConstants.MOY_DIRECT_EXCHANGE_ROUTING_KEY_B);
    }
}
