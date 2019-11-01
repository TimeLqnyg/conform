package cn.zjh.simplewebsocket;

import cn.zjh.simplewebsocket.dao.AuthorityRepository;
import cn.zjh.simplewebsocket.model.Authority;
import cn.zjh.simplewebsocket.model.Packaged;
import cn.zjh.simplewebsocket.service.rabbitmq.simple.HelloRecv;
import cn.zjh.simplewebsocket.service.rabbitmq.simple.HelloSend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
        String routingKey="test.routingKey";
        //第一种
//        rabbitAdmin.declareBinding(new Binding("test.topic.queue",Binding.DestinationType.QUEUE,
//                "test.topic",routingKey,new HashMap<>()));

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

    @Test
    public void testSendMessage(){
		MessageProperties messageProperties=new MessageProperties();
		messageProperties.setContentType("text/plain");
		messageProperties.getHeaders().put("desc","信息描述");
		messageProperties.getHeaders().put("type","自定义消息类型");
		Message message = new Message("Hello RabbitMQ Message".getBytes(), messageProperties);

        rabbitTemplate.convertAndSend("test.topic","test.routingKey",message,changeMessage -> {
            System.out.println("---------额外信息---------");
            changeMessage.getMessageProperties().getHeaders().put("desc","额外信息描述");
            return changeMessage;
        });
	}

	@Test
	public void testSendJson() throws JsonProcessingException {
        Packaged packaged=new Packaged();
        packaged.setId(1);
        packaged.setName("package");
        packaged.setDescription("描述");
        ObjectMapper objectMapper=new ObjectMapper();
        String json =objectMapper.writeValueAsString(packaged);

        MessageProperties messageProperties=new MessageProperties();
        messageProperties.setContentType("application/json");
        Message message=new Message(json.getBytes(),messageProperties);

        rabbitTemplate.send("test.topic","test.json",message);

    }

    @Test
    public void test(){
        String username = rabbitTemplate.getConnectionFactory().getUsername();
        System.out.println(username);
    }

    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    public void addTest(){
        //这样子的话 重复插入会报异常
        Authority authority=new Authority();
        authority.setUsername("张三");
        authority.setAuthority("A");
        authority.setUserId("321ed5ec-7e56-4b1b-b88d-4ac82bb6c902");
        Authority authority1=authorityRepository.save(authority);
        System.out.println(authority1.getUserId());
    }

    @Test
    public void findTest(){
        List<Integer> list=authorityRepository.findAllByUsername("张三");
        list.forEach(System.out::println);
    }

    @Test
    public void deleteTest(){
        authorityRepository.deleteAll();
    }

}
