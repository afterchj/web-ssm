package com;

import com.tpadsz.ssm.rabbit.MessageProducer;
import com.tpadsz.ssm.utils.SpringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by after on 2019/1/13.
 */
public class TempTest {

    public static void main(String[] args) {
        Object obj = "test";
        String str = "test";
        Map<String, Integer> map = new HashMap();

        for (int i = 1; i < 6; i++) {
            map.put("test" + i, i);
        }
        //遍历map中的键
        for (String key : map.keySet()) {
            System.out.println("key = " + key);
        }
        //遍历map中的值
        for (Integer value : map.values()) {
            System.out.println("value = " + value);
        }

        System.out.println(map.size() + "\t" + str.equals(obj) + "\t" + obj.equals(str));
    }

    @Test
    public void testRabbit() throws InterruptedException {
//        Thread.sleep(3000);
        MessageProducer messageProducer = SpringUtils.getProducer();
        System.out.println("amqpTemplate=" + messageProducer);
        for (int i = 101; i < 201; i++) {
//           amqpTemplate.convertAndSend("spring-tpad-blt-console-queue","blt_queue send message " + i);
            if (i % 2 == 0) {
                messageProducer.send();
            } else if (i % 3 == 0) {
                messageProducer.send1(i);
            } else {
                messageProducer.send2(i);
                messageProducer.sendMsg("blt_queue send message " + i);
            }
        }
        Thread.sleep(3000);
    }
}
