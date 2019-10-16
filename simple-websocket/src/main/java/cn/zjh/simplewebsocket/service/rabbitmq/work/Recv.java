package cn.zjh.simplewebsocket.service.rabbitmq.work;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.rabbitmq.work
 * @date:2019/10/16
 */
public class Recv {
    private final static String QUEUE_NAME = "work";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = ConnectionUtils.getConnection();
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();) {
        }
    }
}
