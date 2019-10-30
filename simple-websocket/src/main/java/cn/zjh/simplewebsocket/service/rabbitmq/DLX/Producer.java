package cn.zjh.simplewebsocket.service.rabbitmq.DLX;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.rabbitmq.returnListener
 * @date:2019/10/24
 */
public class Producer {
	public static void main(String[] args) throws Exception {
		ConnectionFactory connectionFactory = ConnectionUtils.getConnection();
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();

		String exchangeName = "test_dlx_exchange";
		String routingKey = "dlx.save";
		String routingErrorKey = "abc.save";

		String meesage = "Hello return";

		channel.addReturnListener(new ReturnListener() {
			@Override
			public void handleReturn(int replyCode, String replyText,
									 String exchange, String routingKey,
									 AMQP.BasicProperties properties,
									 byte[] body) throws IOException {
				System.out.println("--------handle return-----------");
				System.out.println("replyCode: " + replyText);
				System.out.println("exchange: " + exchange);
				System.out.println("routingKey: " + routingKey);
				System.out.println("properties: " + properties);
				System.out.println("body: " + new String(body));

			}
		});


		AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
				.expiration("10000").build();
		//发送  第三个参数mandatory returnListener会监听到不路由的消息 进行处理 否则会直接删除
		channel.basicPublish(exchangeName, routingKey, true, properties, meesage.getBytes());
//        channel.basicPublish(exchangeName,routingErrorKey,true,null,meesage.getBytes());

	}
}
