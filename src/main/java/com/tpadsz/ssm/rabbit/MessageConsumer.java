//package com.tpadsz.ssm.rabbit;
//
//import org.apache.log4j.Logger;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageListener;
//import org.springframework.stereotype.Component;
//
///**
// * Created by hongjian.chen on 2018/12/28.
// */
//
//@Component
//public class MessageConsumer implements MessageListener {
//
//    private Logger logger = Logger.getLogger(MessageConsumer.class);
//
//    @Override
//    public void onMessage(Message message) {
//        logger.info("receive message:{} " + message.getMessageProperties().getReceivedRoutingKey() + " " + new String(message.getBody()));
//    }
//}
