package com.tpadsz.ssm.dubbo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DemoServiceProviderXmlBootstrap {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocation("/conf/dubbo-provider.xml");
        context.refresh();
        System.out.println("DemoService provider (XML) is starting...");
        System.in.read();
    }
}
