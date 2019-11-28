package cn.zjh.simplewebsocket.config;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.config
 * @date:2019/11/28
 */
@Configuration
@Getter
//@ConfigurationProperties(prefix = "rabbit.direct") //需要get&set方法  prefix需要小写
public class RabbitMQConfig {

    /**
     * 交换机名称
     */
    @Value("${rabbit.direct.exchange}")
    private String directExchange;

    /**
     * 队列一名称，对路由键1感兴趣
     */
    @Value("${rabbit.direct.queue.queue01}")
    private String queue01;

    /**
     * 队列二名称，对路由键2感兴趣
     */
    @Value("${rabbit.direct.queue.queue02}")
    private String queue02;

    /**
     * 路由键(1)
     */
    @Value("${rabbit.direct.routing.key.key1}")
    private String routingKey1;

    /**
     * 路由键(2)
     */
    @Value("${rabbit.direct.routing.key.key2}")
    private String routingKey2;


    /**
     * 定义交换器
     * 三个参数解释如下
     * name:交换机名称
     * durable:是否持久化，true表示交换机会被写入磁盘，即使RabbitMQ服务器宕机，也能恢复此交换机
     * autoDelete:表示消息交换机没有在使用时将被自动删除 默认是false
     *
     * @return DirectExchange
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(directExchange, true, false);
    }

    /**
     * 定义队列01
     * 第一个参数是queue：要创建的队列名
     * 第二个参数是durable：是否持久化。如果为true，可以在RabbitMQ崩溃后恢复队列
     * 第三个参数是exclusive：true表示一个队列只能被一个消费者占有并消费
     * 第四个参数是autoDelete：true表示服务器不在使用这个队列是会自动删除它
     * 第五个参数是arguments：其它参数
     */
    @Bean(name = "queue01")
    public Queue queue01() {
        return new Queue(queue01, true, false, false, null);
    }

    /**
     * 定义队列02
     * 参数参考队列01
     */
    @Bean(name = "queue02")
    public Queue queue02() {
        return new Queue(queue02, true, false, false, null);
    }

    /**
     * 绑定队列01
     * @param queue01
     * @param directExchange
     * @return
     */
    @Bean
    public Binding queue01Binding(Queue queue01,DirectExchange directExchange){
        return BindingBuilder.bind(queue01).to(directExchange).with(routingKey1);
    }

    /**
     * 绑定队列02
     * @param queue02
     * @param directExchange
     * @return
     */
    @Bean
    public Binding queue02Binding(Queue queue02,DirectExchange directExchange){
        return BindingBuilder.bind(queue02).to(directExchange).with(routingKey2);
    }

    /**
     * 定义消息转换实例 ，转化成 JSON传输
     *
     * @return Jackson2JsonMessageConverter
     */
    @Bean
    public MessageConverter integrationEventMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 配置启用rabbitmq事务
     *
     * @param connectionFactory connectionFactory
     * @return RabbitTransactionManager
     */
//    @Bean
//    public RabbitTransactionManager rabbitTransactionManager(CachingConnectionFactory connectionFactory) {
//        return new RabbitTransactionManager(connectionFactory);
//    }
}
