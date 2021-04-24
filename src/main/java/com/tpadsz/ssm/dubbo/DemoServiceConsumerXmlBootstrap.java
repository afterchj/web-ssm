package com.tpadsz.ssm.dubbo;

import com.isoft.after.api.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DemoServiceConsumerXmlBootstrap {


    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocation("/conf/dubbo-consumer.xml");
        context.refresh();
        System.out.println("DemoService consumer (XML) is starting...");
        DemoService demoService = context.getBean("demoService", DemoService.class);
        for (int i = 0; i < 10; i++) {
            System.out.println(demoService.sayName("小马哥（jack.mr）"));
        }
        context.close();
    }
}
