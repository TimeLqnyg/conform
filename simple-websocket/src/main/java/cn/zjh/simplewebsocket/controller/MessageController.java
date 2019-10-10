package cn.zjh.simplewebsocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.controller
 * @date:2019/10/10
 */

@RestController
public class MessageController {

    @Autowired
    SimpMessagingTemplate template;

    @MessageMapping("/bulletScreen")
//    @SendTo("/topic/message")
    @SendToUser("/user/name")
    public String broadCast(Message<String> message){
        String msg=message.getPayload();
        System.out.println(msg);
        return "success";
    }

    @GetMapping("/sendMsg")
    public void sendMsg(){
        template.convertAndSend("/topic/message","后端发送message");
    }

    public void sendToUser(){
        template.convertAndSendToUser("name","","发送给单独的用户");
    }


}
