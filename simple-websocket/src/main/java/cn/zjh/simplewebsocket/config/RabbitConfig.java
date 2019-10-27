package cn.zjh.simplewebsocket.config;

import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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
        connectionFactory.setAddresses("192.168.136.250:5672");
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
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
		return rabbitTemplate;
	}


}
