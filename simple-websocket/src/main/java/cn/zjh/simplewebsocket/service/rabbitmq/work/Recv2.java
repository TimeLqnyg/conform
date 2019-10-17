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
 * @date:2019/10/17
 */
public class Recv2 {
    private final static String QUEUE_NAME = "work";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = ConnectionUtils.getConnection();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //// accept only one unack-ed message at a time (see below)
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);
        DeliverCallback deliverCallback = (customerTag, delivery) -> {
            String message = new String(delivery.getBody());

            System.out.println("[x] Received '" + message + "'");
            try {
                doWork(message);
                Thread.sleep(2000);

            } catch (Exception e) {
                System.out.println(e.getMessage());

            } finally {
                System.out.println("[x] Done");
                //手动应答
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        //消息应答  自动应答
        boolean autoAck = false; // acknowledgment is covered below
        channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
        });

    }

    private static void doWork(String message) throws InterruptedException {
        System.out.println("[2] Recv2 message:" + message);
    }
}
