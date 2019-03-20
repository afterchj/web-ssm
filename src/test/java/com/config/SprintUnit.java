package com.config;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class SprintUnit {



    @Test
    public void testDirectConsumer() throws InterruptedException {
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(RabbitMqConfig.class);
//        MessageConsumer consumer = ctx.getBean(MessageConsumer.class);
//        System.out.println("beanName=" + consumer.getClass().getName());
        Map map = new HashMap();
        map.put("ip", "127.0.0.1");
        for (int i = 1; i < 3; i++) {
            map.put("msg", "blt_queue send message " + i);
//            producer.sendMsg(JSON.toJSONString(map));
//            if (i % 2 == 0) {
//                producer.send();
//            } else if (i % 3 == 0) {
//                producer.send1(i);
//            } else {
//                producer.send2(i);
//                producer.sendMsg("blt_queue send message" + i);
//            }
        }
        new Thread().sleep(5000);
    }
}
