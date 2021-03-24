package com.tpadsz.ssm.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by hongjian.chen on 2019/1/15.
 * 配置websocket入口，允许访问的域、注册Handler、SockJs支持和拦截器
 */
@Configuration  //配置类
@EnableWebSocket  //声明支持websocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //String[] allowsOrigins={"http://localhost:8080"};
        //addHandler注册和路由的功能，当客户端发起websocket连接，把/path交给对应的handler处理，而不实现具体的业务逻辑，可以理解为收集和任务分发中心。
        //setAllowedOrigins(String[] domains),允许指定的域名或IP(含端口号)建立长连接，默认只有本地。如果不限时使用"*"号，如果指定了域名，则必须要以http或https开头。
        //addInterceptors，顾名思义就是为handler添加拦截器，可以在调用handler前后加入自定义的逻辑代码。
        registry.addHandler(ChatRoom(), "/chat.do").setAllowedOrigins("*").addInterceptors(handshakeInterceptor());
        //允许客户端使用SockJS
        //SockJS 是一个浏览器上运行的 JavaScript 库，如果浏览器不支持 WebSocket，该库可以模拟对 WebSocket 的支持。
        registry.addHandler(ChatRoom(), "/sockjs/chat.do").addInterceptors(handshakeInterceptor()).withSockJS();

        registry.addHandler(springWebSocketHandler(), "/websocket").setAllowedOrigins("*").addInterceptors(springWebSocketHandlerInterceptor());
//        //允许客户端使用SockJS
//        //SockJS 是一个浏览器上运行的 JavaScript 库，如果浏览器不支持 WebSocket，该库可以模拟对 WebSocket 的支持。
        registry.addHandler(springWebSocketHandler(), "/sockjs/websocket").addInterceptors(springWebSocketHandlerInterceptor()).withSockJS();
    }


    @Bean
    public ChartHandshakeInterceptor handshakeInterceptor() {
        return new ChartHandshakeInterceptor();
    }

    @Bean
    public ChatRoom ChatRoom() {
        return new ChatRoom();
    }

    @Bean
    public SpringWebSocketHandler springWebSocketHandler() {
        return new SpringWebSocketHandler();
    }

    @Bean
    public SpringWebSocketHandlerInterceptor springWebSocketHandlerInterceptor() {
        return new SpringWebSocketHandlerInterceptor();
    }
}