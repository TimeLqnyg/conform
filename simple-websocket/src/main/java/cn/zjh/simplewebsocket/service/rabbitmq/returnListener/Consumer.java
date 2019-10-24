package cn.zjh.simplewebsocket.service.rabbitmq.returnListener;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.rabbitmq.returnListener
 * @date:2019/10/24
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = ConnectionUtils.getConnection();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String exchangeName="test_return_exchange";
        String routingKey="return.#";
        String routingErrorKey="abc.save";
        String queueName="test_return_queue";

        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC,true);
        channel.queueDeclare(queueName,true,false,false,null);
        channel.queueBind(queueName,exchangeName,routingKey);

        channel.basicConsume(queueName,true,(consumerTag, message) -> {
            System.out.println(message.getBody());
        },(consumerTag -> {}));
    }
}
