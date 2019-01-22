package com.tpadsz.ssm.controller;


import com.tpadsz.ssm.utils.ChatUtils;
import com.tpadsz.ssm.websocket.SpringWebSocketHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created by after on 2018/7/31.
 */
@RequestMapping("/room")
@Controller
public class WebsocketController {

    @Bean//这个注解会从Spring容器拿出Bean
    public SpringWebSocketHandler infoHandler() {
        return new SpringWebSocketHandler();
    }

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("userName");
        username = StringUtils.isEmpty(username) ? ChatUtils.getRandomNickName() : username;
        HttpSession session = request.getSession(false);
        session.setAttribute("WEBSOCKET_USERNAME", username);
//        USERNAME
        response.sendRedirect("../websocket.jsp");
//        return new ModelAndView("websocket");
    }

    @ResponseBody
    @RequestMapping("/send")
    public String send(HttpServletRequest request) {
        String username = request.getParameter("userName");
        infoHandler().sendMessageToAll(username, new TextMessage("你好，测试！！！"));
        return username + " send message is success!";
    }
}
