package cn.zjh.simplewebsocket.service.rabbitmq.confirm;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.*;
import org.springframework.amqp.rabbit.listener.BlockingQueueConsumer;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.rabbitmq.confirm
 * @date:2019/10/24
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = ConnectionUtils.getConnection();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "test_confirm_exchange";
        String routingKey = "confirm_save";
        String queueName = "test_confirm_queue";

        //申明exchange和queue
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, true);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
        //创建消费者
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("消费端：" + message);
        };

        CancelCallback cancelCallback = (consumerTag) -> {
        };
        channel.basicConsume(queueName, true, deliverCallback, cancelCallback);
    }
}
