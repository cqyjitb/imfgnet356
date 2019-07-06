package com.hand.hap.message.impl.rabbitmq;

import com.hand.hap.message.IMessagePublisher;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * RabbitMQ 消息推送实现.
 *
 * @author njq.niu@hand-china.com
 */
public class MessagePublisherImpl implements IMessagePublisher {


    @Autowired
    @Qualifier("defaultDirectExchange")
    private DirectExchange directExchange;

    @Autowired
    @Qualifier("defaultTopicExchange")
    private TopicExchange topicExchange;

    @Autowired
    @Qualifier("defaultRabbitTemplate")
    private RabbitTemplate rabbitTemplate;


    public void publish(String channel, Object message) {
        if (message == null) {
            rabbitTemplate.convertAndSend(topicExchange.getName(), channel, "null");
        } else {
            rabbitTemplate.convertAndSend(topicExchange.getName(), channel, message);
        }
    }

    @Override
    @Deprecated
    public void rPush(String list, Object message) {
        message(list, message);
    }

    @Override
    public void message(String name, Object message) {
        if (message == null) {
            rabbitTemplate.convertAndSend(directExchange.getName(), name, "null");
        } else {
            rabbitTemplate.convertAndSend(directExchange.getName(), name, message);
        }
    }

}
