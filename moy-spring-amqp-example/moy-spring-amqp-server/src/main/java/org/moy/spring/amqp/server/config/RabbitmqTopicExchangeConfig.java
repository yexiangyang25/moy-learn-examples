package org.moy.spring.amqp.server.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: [生产者创建绑定配置]</p>
 * Created on 2018/12/09
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
@Configuration
public class RabbitmqTopicExchangeConfig {

    /**
     * 直接点对点模式,支持通配符模式
     *
     * @return
     */
    @Bean
    public TopicExchange moyTopicExchange() {
        return new TopicExchange(MqConstants.MOY_TOPIC_EXCHANGE);
    }

    @Bean
    public Queue moyDirectExchangeQueueA() {
        return new Queue(MqConstants.MOY_TOPIC_EXCHANGE_QUEUE_A);
    }

    @Bean
    public Binding bindingMoyDirectExchangeA() {
        return BindingBuilder.bind(moyDirectExchangeQueueA())
                .to(moyTopicExchange()).with(MqConstants.MOY_TOPIC_EXCHANGE_ROUTING_KEY_A);
    }

    @Bean
    public Queue moyDirectExchangeQueueB() {
        return new Queue(MqConstants.MOY_TOPIC_EXCHANGE_QUEUE_B);
    }

    @Bean
    public Binding bindingMoyDirectExchangeB() {
        return BindingBuilder.bind(moyDirectExchangeQueueB())
                .to(moyTopicExchange()).with(MqConstants.MOY_TOPIC_EXCHANGE_ROUTING_KEY_B);
    }
}
