package cn.zjh.simplewebsocket.service.async;

import cn.zjh.simplewebsocket.model.LogEntity;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.async
 * @date:2019/11/28
 */
@Component
@Slf4j
@RabbitListener(queues = {"${rabbit.direct.queue.queue01}"})
public class AsyncProvider {

    @RabbitHandler
    public void receiver(@Payload LogEntity logEntity, @Headers Channel channel, Message message) throws IOException {

        try {
            log.info("接收到的消息:{}", logEntity.toString());
            TimeUnit.SECONDS.sleep(10);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("确认处理消息:{}", logEntity.toString());
        } catch (Exception e) {
            log.error("消费处理异常:{} - {}", logEntity.toString(), e);
            // 拒绝当前消息，并把消息返回原队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }


    }
}
