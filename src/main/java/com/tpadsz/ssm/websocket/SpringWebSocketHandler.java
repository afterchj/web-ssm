package com.tpadsz.ssm.websocket;


import com.tpadsz.ssm.utils.ChatUtils;
import com.tpadsz.ssm.utils.PropertiesUtils;
import org.apache.log4j.Logger;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by after on 2018/7/31.
 */

public class SpringWebSocketHandler extends AbstractWebSocketHandler {

    private Logger logger = Logger.getLogger(SpringWebSocketHandler.class);
    //ArrayList会出现性能问题，最好用Map来存储，key用userid
    public static final Map<Object, WebSocketSession> users = new HashMap<>();

    public FileOutputStream output;

    /**
     * 连接成功时候，会触发页面上onopen方法
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        super.afterConnectionEstablished(session);
        // TODO Auto-generated method stub
        users.put(session.getAttributes().get("USERNAME"), session);
        session.sendMessage(new TextMessage("I'm " + (session.getAttributes().get("USERNAME"))));
        logger.info("connect to the websocket success......当前数量:" + users.size());
    }

    /**
     * 关闭连接时触发
     */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//        super.afterConnectionClosed(session, closeStatus);
        logger.info("websocket connection closed......");
        Object username = session.getAttributes().get("USERNAME");
        users.remove(username);
        //这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
//        TextMessage returnMessage = new TextMessage(username.toString() + "退出聊天室！");
//        session.sendMessage(returnMessage);
        logger.info("剩余在线用户" + users.size());
    }

    /**
     * js调用websocket.send时候，会调用该方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String user = session.getAttributes().get("USERNAME").toString();
        String payload = message.getPayload();
        TextMessage textMessage = new TextMessage(user + "：" + payload);
        try {
            if (payload.endsWith(":fileStart")) {
                output = new FileOutputStream(new File(PropertiesUtils.getValue("file") + payload.split(":")[0]));
            } else if (payload.endsWith(":fileFinishSingle")) {
                output.close();
                String fileName = payload.split(":")[0];
                for (Map.Entry<Object, WebSocketSession> entry : users.entrySet()) {
                    ChatUtils.sendPicture(entry.getValue(), fileName);
                }
            } else if (payload.endsWith(":fileFinishWithText")) {
                output.close();
                String fileName = payload.split(":")[0];
                for (Map.Entry<Object, WebSocketSession> entry : users.entrySet()) {
                    ChatUtils.sendPicture(entry.getValue(), fileName);
                }
            } else {
                sendMessageToAll(user, textMessage);
            }
        } catch (IOException e) {
            logger.error("异常信息" + e.getMessage());
        }
    }

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        logger.info("处理BinaryMessage..." + message.getPayload().toString());
        ByteBuffer buffer = message.getPayload();
        try {
            output.write(buffer.array());
        } catch (IOException e) {
        }
    }

    public void handleTransportError(WebSocketSession session, Throwable exception){
        try {
            super.handleTransportError(session, exception);
        } catch (Exception e) {
            logger.error("webSocket connection closed......ERROR");
            e.printStackTrace();
        }
        users.remove(session);
    }

    public boolean supportsPartialMessages() {
        return true;
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