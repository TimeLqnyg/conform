package cn.zjh.simplewebsocket;

import cn.zjh.simplewebsocket.service.rabbitmq.simple.HelloRecv;
import cn.zjh.simplewebsocket.service.rabbitmq.simple.HelloSend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleWebsocketApplicationTests {


//    @Autowired
//    private HelloSend helloSend;
//
//    @Autowired
//    private HelloRecv helloRecv;

    @Test
    public void contextLoads() {
    }

//    @Test
//    public void test() throws Exception{
//        helloSend.send();
//    }
//
//    @Test
//    public void recv()throws Exception{
//        helloRecv.recv();
//    }

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Test
    public void testAdmin(){

        TopicExchange topicExchange=new TopicExchange("test.topic",false,false);
        rabbitAdmin.declareExchange(topicExchange);

        Queue topicQueue=new Queue("test.topic.queue",false);
        rabbitAdmin.declareQueue(topicQueue);

        /**
         * String destination, DestinationType destinationType, String exchange, String routingKey,Map<String, Object> arguments
         */
        String routingKey="test.routingkey";
        //第一种
        rabbitAdmin.declareBinding(new Binding("test.topic.queue",Binding.DestinationType.QUEUE,
                "test.topic",routingKey,new HashMap<>()));

        //第二种
        rabbitAdmin.declareBinding(BindingBuilder
                .bind(topicQueue)
                .to(topicExchange)
                .with(routingKey));

        //清空队列
        rabbitAdmin.purgeQueue(topicQueue.getName(),false);
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void testSendMessage(){
		MessageProperties messageProperties=new MessageProperties();
		messageProperties.getHeaders().put("desc","信息描述");
		messageProperties.getHeaders().put("type","自定义消息类型");

	}
}
