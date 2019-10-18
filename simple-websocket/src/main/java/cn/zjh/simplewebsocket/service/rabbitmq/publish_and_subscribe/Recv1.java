package cn.zjh.simplewebsocket.service.rabbitmq.publish_and_subscribe;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.publish_and_subscribe
 * @date:2019/10/17
 */
public class Recv1 {
    private static final String EXCHANGE_NAME="exchange";
//    private final static String QUEUE_NAME = "subscribe1";
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = ConnectionUtils.getConnection();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String queueName=channel.queueDeclare().getQueue();
        System.out.println(queueName);
        channel.queueBind(queueName,EXCHANGE_NAME,"");
        channel.basicQos(1);

        DeliverCallback deliverCallback = (customerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println(queueName+" Received '" + message + "'");
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

        boolean autoAck = false; // acknowledgment is covered below
        channel.basicConsume(queueName, autoAck, deliverCallback, consumerTag -> {
        });

    }
}
