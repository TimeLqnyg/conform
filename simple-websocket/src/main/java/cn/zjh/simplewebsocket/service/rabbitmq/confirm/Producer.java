package cn.zjh.simplewebsocket.service.rabbitmq.confirm;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.rabbitmq.confirm
 * @date:2019/10/24
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = ConnectionUtils.getConnection();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //指定消息的确认模式
        channel.confirmSelect();

        String exchangeName = "test_confirm_exchange";
        String routingKey = "confirm_save";

        //发送一条消息
        String message = "Hello rabbitMQ confirm";
        //发送到指定的queue中
        channel.basicPublish(exchangeName, routingKey, null, message.getBytes("UTF-8"));

        //添加一个监听
        channel.addConfirmListener(new ConfirmListener() {
            //成功
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("------Ack-------");
            }

            //失败 没有确认
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("------No Ack-------");
            }
        });

        //这里为了显示 都没有关闭连接

    }
}
