package cn.zjh.simplewebsocket.service.springbootRabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.springbootRabbitMQ
 * @date:2019/10/29
 */
@Component
public class RabbitMQSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Object message, Map<String,Object> properties) {
        MessageHeaders messageHeaders = new MessageHeaders(properties);
        Message msg = MessageBuilder.createMessage(message, messageHeaders);
        rabbitTemplate.convertAndSend("test.topic","test.send",msg);
    }
}

