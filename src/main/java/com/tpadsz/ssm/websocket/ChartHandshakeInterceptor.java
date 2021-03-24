package com.tpadsz.ssm.websocket;

import java.util.Map;
import java.util.Random;

import com.tpadsz.ssm.utils.ChatUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;

/**
 * Created by hongjian.chen on 2019/1/15.
 */
public class ChartHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Map<String, Object> attributes) throws Exception {
        //attributes是session里面的所有属性的map表示
        logger.info("Before Handshake...");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                //使用userName区分WebSocketHandler，以便定向发送消息
                String userName = (String) session.getAttribute("WEBSOCKET_USERNAME");
                if (StringUtils.isEmpty(userName)) {
                    userName = ChatUtils.getRandomNickName();
                }
                attributes.put("user", userName);
            }
        }
        return super.beforeHandshake(request, response, handler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }

}
