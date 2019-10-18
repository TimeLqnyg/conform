package cn.zjh.simplewebsocket.service.rabbitmq.publish_and_subscribe;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.publish_and_subscribe
 * @date:2019/10/17
 */
public class Send {

    private static final String EXCHANGE_NAME="exchange";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = ConnectionUtils.getConnection();

        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();) {
            //声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
            String message="hello exchange";
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());

        }
    }
}
