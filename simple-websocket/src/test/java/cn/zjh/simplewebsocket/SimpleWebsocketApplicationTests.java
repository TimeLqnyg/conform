package cn.zjh.simplewebsocket;

import cn.zjh.simplewebsocket.service.rabbitmq.simple.HelloRecv;
import cn.zjh.simplewebsocket.service.rabbitmq.simple.HelloSend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
