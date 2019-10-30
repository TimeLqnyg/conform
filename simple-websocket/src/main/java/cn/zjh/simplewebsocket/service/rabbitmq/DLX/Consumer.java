package cn.zjh.simplewebsocket.service.rabbitmq.DLX;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

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

        String exchangeName="test_dlx_exchange";
        String routingKey="dlx.#";
        String routingErrorKey="dlx.save";
        String queueName="test_dlx_queue";

        //这个是正常的队列
        //exchange,queue都是有arguments的 自身属性
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC,true,false,null);

		Map<String,Object> arguments=new HashMap<>();
		arguments.put("x-dead-letter-exchange","dlx_exchange");
		//这个arguemnts声明到队列上
		channel.queueDeclare(queueName,true,false,false,arguments);
		channel.queueBind(queueName,exchangeName,routingKey);

		//声明死信队列
		channel.exchangeDeclare("dlx_exchange",BuiltinExchangeType.TOPIC,true,false,null);
		channel.queueDeclare("dlx_queue",true,false,false,null);
		channel.queueBind("dlx_queue","dlx_exchange","#")	;

		//


		/**
		 * 限流
		 *	autoAct=false
		 *
		 *  false表示是consumer级别的
		 */

//		channel.basicQos(0,1,false);


		/**
		 * Delivery 类
		 * private final Envelope _envelope;
		 * private final AMQP.BasicProperties _properties;
		 * private final byte[] _body;
		 */



		//不消费
//        channel.basicConsume(queueName,false,(consumerTag, message) -> {
//            System.out.println(new String(message.getBody()));
//            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
//        },(consumerTag -> {}));
    }
}
