package cn.zjh.simplewebsocket.service.rabbitmq.rpc;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.rabbitmq.rpc
 * @date:2019/10/23
 */
public class Send {


    public String client(String message) throws Exception {
        final String corrId = UUID.randomUUID().toString();
        ConnectionFactory factory = ConnectionUtils.getConnection();

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();) {
            //回复队列  放返回的信息  消息发送方在后续消费这个队列
            String callbackQueueName = channel.queueDeclare().getQueue();
            BasicProperties props = new BasicProperties
                    .Builder()
                    .replyTo(callbackQueueName)
                    .build();

            channel.basicPublish("",  "rpc_queue", props, message.getBytes());

            final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);

            DeliverCallback deliverCallback=(consumerTag, delivery) -> {
                if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                    response.offer(new String(delivery.getBody(), "UTF-8"));
                }
            };

            String ctag=channel.basicConsume(callbackQueueName,true,deliverCallback,consumerTag -> {});

            String result = response.take();
            channel.basicCancel(ctag);
            return result;


        }
    }
}
