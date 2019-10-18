package cn.zjh.simplewebsocket.service.rabbitmq.topic;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.*;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.routing
 * @date:2019/10/18
 */
public class Recv2 {
    private static final String EXCHANGE_NAME="exchange_topic";
    //    private final static String QUEUE_NAME = "subscribe1";
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = ConnectionUtils.getConnection();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //还是要申明的
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String queueName=channel.queueDeclare().getQueue();
        System.out.println(queueName);
        String routingKey="*.topic";
        channel.queueBind(queueName,EXCHANGE_NAME,routingKey);
        channel.basicQos(1);

        DeliverCallback deliverCallback = (customerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println(queueName+" error Received '" + message + "'");
            //手动回复
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

        boolean autoAck = false; // acknowledgment is covered below
        channel.basicConsume(queueName, autoAck, deliverCallback, consumerTag -> {
        });

    }
}

