package cn.zjh.simplewebsocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.config
 * @date:2019/10/10
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/zjhWs").withSockJS(); //注册一个websocket的服务端端点
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //允许客户端发送的信息的路径的前缀
        registry.setApplicationDestinationPrefixes("/test");
        //这个是主题  订阅和广播  客户端订阅   服务器发送
        registry.enableSimpleBroker("/topic","/user");
    }
}
