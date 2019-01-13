package com.tpadsz.ssm.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;

/**
 * Created by zhm on 2015/7/14.
 */
public class WebSocketEndPoint extends TextWebSocketHandler {
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
                } else {
                    users.remove(user.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
