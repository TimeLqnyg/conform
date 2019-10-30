package cn.zjh.simplewebsocket.service.rabbitmq.returnListener;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.*;

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

		/**
		 * 限流
		 *	autoAct=false
		 *
		 *  false表示是consumer级别的
		 */

		channel.basicQos(0,1,false);


		/**
		 * Delivery 类
		 * private final Envelope _envelope;
		 * private final AMQP.BasicProperties _properties;
		 * private final byte[] _body;
		 */



        channel.basicConsume(queueName,false,(consumerTag, message) -> {
            System.out.println(new String(message.getBody()));
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
            //最后一个参数 requeue 重回队列
//            channel.basicNack(message.getEnvelope().getDeliveryTag(),false,true);
        },(consumerTag -> {}));
    }
}
