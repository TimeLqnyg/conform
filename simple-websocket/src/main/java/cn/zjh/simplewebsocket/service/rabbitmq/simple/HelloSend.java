package cn.zjh.simplewebsocket.service.rabbitmq.simple;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.rabbitmq
 * @date:2019/10/16
 */
//@Component
public class HelloSend {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args)throws Exception {
        ConnectionFactory factory = ConnectionUtils.getConnection();
        try (
                Connection connection= factory.newConnection();
                Channel channel=connection.createChannel();
        ){
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            String message="hello world";
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes("UTF-8"));
            System.out.println("Send '"+message+"'");

        }
    }

//    public void send()throws Exception{
//
//    }
}
