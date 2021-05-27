//package com.tpadsz.ssm.service;
//
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
//
//@Service
//public class RabbitmqService {
//
////    @Resource(name = "amqpTemplate")
//    private RabbitTemplate rabbitTemplate;
//
//    public void sendMsg(String s) {
//        rabbitTemplate.convertAndSend("hello " + s);
//    }
//
//    public void getMessage(){
//        System.out.println("receive msg="+rabbitTemplate.receive().getBody().toString());;
//    }
//}
//
