package cn.zjh.simplewebsocket.service.rabbitmq.topic;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.*;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.topic
 * @date:2019/10/18
 */
public class Send {

    private static final String EXCHANGE_NAME = "exchange_topic";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = ConnectionUtils.getConnection();

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();) {
            //声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            String routingKey = "hello.topic";
            String message = "hello topic";
            channel.basicPublish(EXCHANGE_NAME, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        }
    }
}
