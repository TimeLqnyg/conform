package cn.zjh.simplewebsocket.config;

import com.rabbitmq.client.Channel;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.config
 * @date:2019/10/11
 */

//rabbitmq config
@Configuration
//@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "rabbitmq",ignoreInvalidFields = true) //需要get&set方法
public class RabbitConfig {

//    @Value("${rabbitmq.host}")
    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

//    @Bean
//    public ConnectionFactory connectionFactory(){
//        System.out.println(host);
//        //properties
//        return new CachingConnectionFactory(host);
//    }

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory=new CachingConnectionFactory();
//        connectionFactory.setAddresses("192.168.136.250:5672");
        connectionFactory.setAddresses("192.168.204.250:5672");
        connectionFactory.setVirtualHost("/vhost_zjh");
        connectionFactory.setUsername("user_zjh");
        connectionFactory.setPassword("user_zjh");
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin rabbitAdmin=new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean
    public Queue queue(){
        return new Queue("queue",true); //持久化
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
		return rabbitTemplate;
	}

	//这个监听器 监听指定的队列的 所产生的变化
	@Bean
	public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
        final SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
//        simpleMessageListenerContainer.setQueues(queue());
        simpleMessageListenerContainer.setQueueNames("test.topic.queue");
        simpleMessageListenerContainer.setConcurrentConsumers(1);
        simpleMessageListenerContainer.setMaxConcurrentConsumers(5);
        simpleMessageListenerContainer.setDefaultRequeueRejected(false);
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
        simpleMessageListenerContainer.setConsumerTagStrategy(queue -> {
            return queue;
        });

        /**

        simpleMessageListenerContainer.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                String msg=new String(message.getBody());
                System.out.println("---------消费者"+msg);
            }

        });
         */

        //只要消息发送到queue中就触发
        MessageListenerAdapter adapter=new MessageListenerAdapter(new MessageDelegate());
        //修改默认的方法
//        adapter.setDefaultListenerMethod("consumerMessage");
        //类型转换
        adapter.setMessageConverter(new MessageConverter() {
            @Override
            public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {

                return new Message(object.toString().getBytes(),messageProperties);
            }

            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                final String contentType = message.getMessageProperties().getContentType();
                if(contentType!=null&&contentType.contains("text")){
                    return new String(message.getBody());
                }
                return message.getBody();
            }
        });
        Map<String,String> queueOrTagToMethodName=new HashMap<>();
        queueOrTagToMethodName.put("test.topic.queue","method1");
        adapter.setQueueOrTagToMethodName(queueOrTagToMethodName);
        simpleMessageListenerContainer.setMessageListener(adapter);


        return simpleMessageListenerContainer;
    }

}

class MessageDelegate{
    //default listener method: "handleMessage".
    //这里是方法
    void handleMessage(byte[] message){
        System.out.println("默认方法"+new String(message));
    }

    void consumerMessage(String message){
        System.out.println("自定义方法"+message);
    }

    void method1(String message){
        System.out.println("自定义方法method1"+message);
    }
}
