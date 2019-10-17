package cn.zjh.simplewebsocket.service.rabbitmq.work;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.rabbitmq.work
 * @date:2019/10/16
 */
public class Recv1 {
    private final static String QUEUE_NAME = "work";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = ConnectionUtils.getConnection();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicQos(1);
        DeliverCallback deliverCallback = (customerTag, delivery) -> {
            String message = new String(delivery.getBody());

            System.out.println("[x] Received '" + message + "'");
            try {
                doWork(message);
                Thread.sleep(1000);

            } catch (Exception e) {
                System.out.println(e.getMessage());

            } finally {
                System.out.println("[x] Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        boolean autoAck = false; // acknowledgment is covered below
        channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
        });

    }

    private static void doWork(String message) throws InterruptedException {
        System.out.println("[1] Recv1 message:" + message);
    }
}
