//package com.tpadsz.ssm.service.rabbit;
//
//import com.tpadsz.ssm.utils.PropertiesUtils;
//import org.apache.log4j.Logger;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * Created by hongjian.chen on 2019/1/23.
// */
//
//@Component
//public class MessageProducer {
//
//    private Logger logger = Logger.getLogger(MessageProducer.class);
//
//    @Autowired
//    private AmqpTemplate rabbitTemplate;
//
//    public void send() {
//        String context = "hi, i am come from spring_exchange message all";
//        rabbitTemplate.convertAndSend("spring_exchange", "topic.test.message ", context);
//    }
//
//    public void send1(int i) {
//        String context = "hi, i am message * ";
//        rabbitTemplate.convertAndSend("demoExchange", "topic.demo.message ", context + i);
//    }
//
//    public void send2(int i) {
//        String context = "hi, i am messages # ";
//        rabbitTemplate.convertAndSend("demoExchange", "topic.messages", context + i);
//    }
//
//    public void sendMsg(String num) {
//        rabbitTemplate.convertAndSend(PropertiesUtils.getValue("rabbitmq.queue"), num);
//    }
//}
