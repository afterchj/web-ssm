package com.tpadsz.ssm.service.impl;


import com.isoft.after.api.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class DemoConsumer {

//    @DubboReference(group = "${dubbo.group.name}", version = "${dubbo.provider.version}")
    @DubboReference(group = "dev", version = "0.1.0",check = false)
    private DemoService demoService;

    public String test(String s) {
        return demoService.sayName(s);
    }
}
