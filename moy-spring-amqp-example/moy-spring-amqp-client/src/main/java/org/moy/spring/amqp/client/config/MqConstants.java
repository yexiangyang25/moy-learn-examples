package org.moy.spring.amqp.client.config;

/**
 * <p>Description: [amqp 常量]</p>
 * Created on 2018/12/09
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public interface MqConstants {

    String MOY_QUEUE = "moy_queue";

    String MOY_FANOUT_EXCHANGE = "moy_fanout_exchange";
    String MOY_FANOUT_EXCHANGE_QUEUE_A = "moy_fanout_exchange_queue_a";
    String MOY_FANOUT_EXCHANGE_QUEUE_B = "moy_fanout_exchange_queue_b";
    String MOY_FANOUT_EXCHANGE_QUEUE_C = "moy_fanout_exchange_queue_c";

    String MOY_DIRECT_EXCHANGE = "moy_direct_exchange";
    String MOY_DIRECT_EXCHANGE_QUEUE_A = "moy_direct_exchange_queue_a";
    String MOY_DIRECT_EXCHANGE_ROUTING_KEY_A = "moy_direct_exchange_routing_key_a";
    String MOY_DIRECT_EXCHANGE_QUEUE_B = "moy_direct_exchange_queue_b";
    String MOY_DIRECT_EXCHANGE_ROUTING_KEY_B = "moy_direct_exchange_routing_key_b";

    /**
     * 点对点模型，支持模糊匹配
     */
    String MOY_TOPIC_EXCHANGE = "moy_topic_exchange";
    String MOY_TOPIC_EXCHANGE_QUEUE_A = "moy_topic_exchange_queue_a";
    String MOY_TOPIC_EXCHANGE_ROUTING_KEY_A = "moy_topic_exchange_routing_key_a";
    String MOY_TOPIC_EXCHANGE_QUEUE_B = "moy_topic_exchange_queue_b";
    String MOY_TOPIC_EXCHANGE_ROUTING_KEY_B = "moy_topic_exchange_routing_key_b";
}
