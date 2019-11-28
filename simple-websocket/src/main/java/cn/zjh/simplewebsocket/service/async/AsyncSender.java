package cn.zjh.simplewebsocket.service.async;

import cn.zjh.simplewebsocket.config.RabbitMQConfig;
import cn.zjh.simplewebsocket.model.LogEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.async
 * @date:2019/11/28
 */
@Component
@Slf4j
public class AsyncSender implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQConfig rabbitMQConfig;

    @PostConstruct
    private void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setChannelTransacted(true);
    }

//    @Transactional
    public Boolean handleSysLog(LogEntity logEntity){
        rabbitTemplate.convertAndSend(rabbitMQConfig.getDirectExchange(),rabbitMQConfig.getRoutingKey1(),logEntity);
        return Boolean.TRUE;
    }

    /**
     * 消息发送到交换器Exchange后触发回调。(应该是消费后，有ack再回调)
     * 使用该功能需要开启确认，spring-boot中配置如下
     * spring.rabbitmq.publisher-confirms = true
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息已确认 cause:{} - {}", cause, correlationData);
        } else {
            log.error("消息未确认 cause:{} - {}", cause, correlationData);
        }

    }

    /**
     * 通过实现ReturnCallback接口，
     * 如果消息从交换器发送到对应队列失败时触发
     * 比如根据发送消息时指定的routingKey找不到队列时会触发
     * 使用该功能需要开启确认，spring-boot中配置如下：
     * spring.rabbitmq.publisher-returns = true
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("消息被退回:{}", message);
        log.error("消息使用的交换机:{}", exchange);
        log.error("消息使用的路由键:{}", routingKey);
        log.error("描述:{}", replyText);

    }
}
