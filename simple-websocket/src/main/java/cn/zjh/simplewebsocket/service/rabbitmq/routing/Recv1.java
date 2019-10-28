package cn.zjh.simplewebsocket.service.rabbitmq.routing;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.*;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.routing
 * @date:2019/10/18
 */
public class Recv1 {
    private static final String EXCHANGE_NAME="exchange_direct";
        private final static String QUEUE_NAME = "subscribe1";
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = ConnectionUtils.getConnection();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        String queueName=channel.queueDeclare().getQueue();
        System.out.println(QUEUE_NAME);
        String routingKey="hello";
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,routingKey);
        channel.basicQos(1);

        DeliverCallback deliverCallback = (customerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println(QUEUE_NAME+" hello Received '" + message + "'");
            //手动回复
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

        boolean autoAck = false; // acknowledgment is covered below
        channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
        });

    }
}
