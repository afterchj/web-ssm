package com.tpadsz.ssm.controller;

import com.isoft.after.api.ExternService;
import com.isoft.after.constants.Result;
import com.isoft.after.model.dto.UserDTO;
import com.isoft.after.utils.ResponseUtil;
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
    private ExternService externService;

    @RequestMapping(method = RequestMethod.GET)
    public Result<UserDTO> show(String username, String password) {
        log.warn("externService {}", externService);
        return ResponseUtil.SUCCESS(externService.login(username, password));
    }

}
