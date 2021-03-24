package com.tpadsz.after.aop03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by hongjian.chen on 2019/1/2.
 */

@Configuration
@ComponentScan(basePackages="com.tpadsz.after.aop03")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ApplicationCfg {

    @Bean
    public UserService userService(){
        return new UserService();
    }
}
