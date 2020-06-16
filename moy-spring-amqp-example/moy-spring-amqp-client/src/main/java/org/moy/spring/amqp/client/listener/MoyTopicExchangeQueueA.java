package org.moy.spring.amqp.client.listener;


import com.rabbitmq.client.Channel;
import org.moy.spring.amqp.client.config.MqConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * <p>Description: [amqp 接受者]</p>
 * Created on 2018/12/09
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
@Component
public class MoyTopicExchangeQueueA {

    private static Logger LOG = LoggerFactory.getLogger(MoyTopicExchangeQueueA.class);

    /**
     * 消费者创建绑定配置
     *
     * @param context
     * @param channel
     * @param message
     * @throws IOException
     */
    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MqConstants.MOY_TOPIC_EXCHANGE_QUEUE_A, durable = "true"),
            exchange = @Exchange(name = MqConstants.MOY_TOPIC_EXCHANGE,
                    type = ExchangeTypes.TOPIC,
                    ignoreDeclarationExceptions = "true"),
            key = {MqConstants.MOY_TOPIC_EXCHANGE_ROUTING_KEY_A}
    ))
    public void process(String context, Channel channel, Message message) throws IOException {
        boolean basicNack = true;
        try {
            LOG.info("接受到消息: {}", context);
            // 处理成功 确认消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            basicNack = false;
        } finally {
            // 处理失败 拒收消息
            if (basicNack) {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }
}
