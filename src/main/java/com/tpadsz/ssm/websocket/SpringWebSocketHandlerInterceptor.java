package com.tpadsz.ssm.websocket;


import com.tpadsz.ssm.utils.AppUtils;
import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Random;

/**
 * Created by after on 2018/7/31.
 */

/**
 * WebSocket拦截器
 *
 * @author WANG
 */

public class SpringWebSocketHandlerInterceptor extends HttpSessionHandshakeInterceptor {

    private static Logger logger = Logger.getLogger(SpringWebSocketHandlerInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // TODO Auto-generated method stub
        String userName = "";
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                //使用userName区分WebSocketHandler，以便定向发送消息
                userName = (String) session.getAttribute("WEBSOCKET_USERNAME");
                if (userName == null) {
                    userName = "default-system" + new Random().nextInt(10);
                }
                attributes.put("USERNAME", userName);
            }
        }
        logger.info("ip=" + AppUtils.getRemoteIP(null) + ",userName=" + userName);
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        // TODO Auto-generated method stub
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
