package com.tpadsz.ssm.websocket;


import com.tpadsz.ssm.utils.ZipUtils;
import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by after on 2018/7/31.
 */

public class SpringWebSocketHandler extends TextWebSocketHandler {

    private static Logger logger = Logger.getLogger(SpringWebSocketHandler.class);
    //ArrayList会出现性能问题，最好用Map来存储，key用userid
    private static final Map<Object, WebSocketSession> users = new HashMap<>();

    /**
     * 连接成功时候，会触发页面上onopen方法
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        super.afterConnectionEstablished(session);
        // TODO Auto-generated method stub
        users.put(session.getAttributes().get("USERNAME"), session);
        //这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
//        TextMessage returnMessage = new TextMessage("你将收到的离线");
//        session.sendMessage(returnMessage);
        System.out.println("connect to the websocket success......当前数量:" + users.size());
    }

    /**
     * 关闭连接时触发
     */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("websocket connection closed......");
        Object username = session.getAttributes().get("USERNAME");
        users.remove(username);
        System.out.println("剩余在线用户" + users.size());
        super.afterConnectionClosed(session, closeStatus);
    }

    /**
     * js调用websocket.send时候，会调用该方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
//        try {
//            super.handleTextMessage(session, message);
//        } catch (Exception e) {
//            logger.error("消息发送失败！");
//        }
        String msg = message.getPayload();
        System.out.println("msg=" + msg);
        String user = session.getAttributes().get("USERNAME").toString();
        TextMessage textMessage = new TextMessage(user + "：" + msg);
        sendMessageToAll(user, textMessage);

    }

    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        logger.debug("websocket connection closed......");
        users.remove(session);
    }

    public boolean supportsPartialMessages() {
        return false;
    }


    /**
     * 给某个用户发送消息
     *
     * @param user
     * @param message
     */
    public void sendMessageToUser(String user, TextMessage message) {
        for (Map.Entry<Object, WebSocketSession> entry : users.entrySet()) {
            if (entry.getKey().equals(user)) {
                if (entry.getValue().isOpen()) {
                    try {
                        entry.getValue().sendMessage(message);
                    } catch (IOException e) {
                        logger.error("给用户发送消息失败！" + user);
                    }
                }
                break;
            }
        }
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToAll(String user, TextMessage message) {
        logger.info("给所有在线用户发送消息=" + message.getPayload());
        try {
            for (Map.Entry<Object, WebSocketSession> entry : users.entrySet()) {
                if (entry.getValue().isOpen()) {
                    if (!entry.getKey().equals(user)) {
                        entry.getValue().sendMessage(message);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("给所有在线用户发送消息！" + e.getCause());
        }
    }

}