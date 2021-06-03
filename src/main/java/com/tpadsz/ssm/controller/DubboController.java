package com.tpadsz.ssm.controller;

import com.isoft.after.api.DemoService;
import com.isoft.after.api.ExternService;
import com.isoft.after.constants.Result;
import com.isoft.after.model.dto.UserDTO;
import com.isoft.after.utils.ResponseUtil;
import com.tpadsz.ssm.service.impl.DemoConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by hongjian.chen on 2018/12/11.
 */

@Slf4j
@RestController
@RequestMapping("/zoo")
public class DubboController {


    @Autowired
    private ExternService externService;

    @DubboReference(group = "dev", version = "0.2.0",check = false)
    private DemoService demoService;

    @Value("${dubbo.provider.version}")
    private String version;

    @Autowired
    private DemoConsumer demoConsumer;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        log.warn("version {}", version);
        return demoConsumer.test("test_" + version);
    }

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String demo() {
        log.warn("version {}", version);
        return demoService.sayName("demo_" + version);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result<UserDTO> show(String username, String password) {
        log.warn("externService {}", externService);
        return ResponseUtil.SUCCESS(externService.login(username, password));
    }

}
