package com.tpadsz.ssm.websocket;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;

/**
 * Created by after on 2018/7/14.
 */
public class WebSocketEndHandler extends TextWebSocketHandler {

    private static Logger logger = Logger.getLogger(WebSocketEndHandler.class);
    private static final ArrayList<WebSocketSession> users;

    static {
        users = new ArrayList();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        users.add(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        users.remove(session);
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        users.remove(session);
        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        TextMessage returnMessage = new TextMessage(session.getAttributes().get("USERNAME") + " : " + message.getPayload());
//        session.sendMessage(returnMessage);
        sendToAllClients(returnMessage, session);
    }

    private void sendToAllClients(TextMessage msg, WebSocketSession curSession) {
        try {
            for (WebSocketSession user : users) {
                if (user.isOpen()) {
                    if (!user.getId().equals(curSession.getId())) {
                        user.sendMessage(msg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
