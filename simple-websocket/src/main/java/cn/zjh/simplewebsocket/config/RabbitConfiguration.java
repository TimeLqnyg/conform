package cn.zjh.simplewebsocket.config;

import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
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
@ConfigurationProperties(prefix = "rabbitmq",ignoreInvalidFields = true) //get&set
public class RabbitConfiguration {

//    @Value("${rabbitmq.host}")
    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Bean
    public ConnectionFactory connectionFactory(){
        System.out.println(host);
        //properties
        return new CachingConnectionFactory(host);
    }
}
