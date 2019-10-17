package cn.zjh.simplewebsocket.service.rabbitmq.work;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.rabbitmq.work
 * @date:2019/10/17
 */
public class Send {

    private final static String QUEUE_NAME = "work";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = ConnectionUtils.getConnection();
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();) {
            //申明队列
            //这个是queue持久化 为true
            boolean durable = false; //为false的话服务器重启后queue就不存在了  反之 则存在
            channel.queueDeclare(QUEUE_NAME,durable,false,false,null);
            for(int i=0;i<50;i++){
                String message="Hello "+i;

                //messageProperties  message消息持久化  persistent_text_plain
                channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
                Thread.sleep(20);

            }
        }
    }


}
