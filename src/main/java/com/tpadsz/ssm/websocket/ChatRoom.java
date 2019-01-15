package com.tpadsz.ssm.websocket;

/**
 * Created by hongjian.chen on 2019/1/15.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class ChatRoom extends AbstractWebSocketHandler {

    public final static List<WebSocketSession> sessionList = Collections.synchronizedList(new ArrayList<WebSocketSession>());
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Logger logger = Logger.getLogger(this.getClass());
    FileOutputStream output;

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("Connection established...ip=" + webSocketSession.getRemoteAddress());
        logger.info(webSocketSession.getAttributes().get("user") + " Login");
        webSocketSession.sendMessage(new TextMessage("I'm " + (webSocketSession.getAttributes().get("user"))));
        sessionList.add(webSocketSession);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) throws Exception {
        logger.info("Connection closed..." + webSocketSession.getRemoteAddress() + " " + status);
        logger.info(webSocketSession.getAttributes().get("user") + " Logout");
        sessionList.remove(webSocketSession);
    }

    @Override
    public void handleTextMessage(WebSocketSession websocketsession, TextMessage message) {
        String payload = message.getPayload();
        logger.info(websocketsession.getAttributes().get("user") + "：" + payload);
        String textString;
        try {
            if (payload.endsWith(":fileStart")) {
                output = new FileOutputStream(new File("D:\\images\\" + payload.split(":")[0]));
            } else if (payload.endsWith(":fileFinishSingle")) {
                output.close();
                String fileName = payload.split(":")[0];
                for (WebSocketSession session : sessionList) {
                    if (session.getId().equals(websocketsession.getId())) {
                        textString = " I (" + format.format(new Date()) + ")<br>";
                    } else {
                        textString = websocketsession.getAttributes().get("user") + " (" + format.format(new Date()) + ")<br>";
                    }
                    TextMessage textMessage = new TextMessage(textString);
                    session.sendMessage(textMessage);
                    sendPicture(session, fileName);
                }
            } else if (payload.endsWith(":fileFinishWithText")) {
                output.close();
                String fileName = payload.split(":")[0];
                for (WebSocketSession session : sessionList) {
                    sendPicture(session, fileName);
                }
            } else {
                for (WebSocketSession session : sessionList) {
                    if (session.getId().equals(websocketsession.getId())) {
                        textString = " I (" + format.format(new Date()) + ")<br>" + payload;
                    } else {
                        textString = websocketsession.getAttributes().get("user") + " (" + format.format(new Date()) + ")<br>" + payload;
                    }
                    TextMessage textMessage = new TextMessage(textString);
                    session.sendMessage(textMessage);
                }
            }
        } catch (IOException e) {
            logger.error("消息处理失败！" + e.getMessage());
        }
    }

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        logger.info(session.getAttributes().get("user") + "：在处理二进制文件...." + output);
        ByteBuffer buffer = message.getPayload();
        try {
            output.write(buffer.array());
        } catch (IOException e) {
            logger.error("二进制消息接收失败！" + e.getMessage());
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        logger.error(throwable.toString());
        logger.info("WS connection error,close..." + webSocketSession.getRemoteAddress());
    }

    @Override
    public boolean supportsPartialMessages() {
        return true;
    }

    public void sendPicture(WebSocketSession session, String fileName) {
        FileInputStream input = null;
        try {
            File file = new File("D:\\images\\" + fileName);
            input = new FileInputStream(file);
            byte bytes[] = new byte[(int) file.length()];
            input.read(bytes);
            BinaryMessage byteMessage = new BinaryMessage(bytes);
            session.sendMessage(byteMessage);
        } catch (Exception e) {
            logger.error("文件发送失败！" + e.getMessage());
        }
    }
}
