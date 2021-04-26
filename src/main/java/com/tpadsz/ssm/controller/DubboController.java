package com.tpadsz.ssm.controller;

import com.isoft.after.api.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DemoService externService;

    @RequestMapping(method = RequestMethod.GET)
    public String show() {
        log.warn("externService {}", externService);
        String s = String.format("%s","本地测试");
        return externService == null ? s : externService.sayName(s);
    }

}
