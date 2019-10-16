package cn.zjh.simplewebsocket.util;

import com.rabbitmq.client.ConnectionFactory;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.util
 * @date:2019/10/16
 */
public class ConnectionUtils {
    public static ConnectionFactory getConnection() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.204.250");
        factory.setPort(5672);
        factory.setVirtualHost("/vhost_zjh");
        factory.setUsername("user_zjh");
        factory.setPassword("user_zjh");
        return factory;
    }
}
