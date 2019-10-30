package cn.zjh.simplewebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("cn.zjh.simplewebsocket.model")
public class SimpleWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleWebsocketApplication.class, args);
    }

}
