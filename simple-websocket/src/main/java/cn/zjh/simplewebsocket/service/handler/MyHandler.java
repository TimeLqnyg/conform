package cn.zjh.simplewebsocket.service.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.handler
 * @date:2019/10/11
 */

/**
 * Creating a WebSocket server is as simple as implementing WebSocketHandler or, more likely,
 * extending either TextWebSocketHandler or BinaryWebSocketHandler.
 */
public class MyHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }
}
