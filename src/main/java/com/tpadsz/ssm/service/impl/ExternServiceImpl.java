package com.tpadsz.ssm.service.impl;

import com.isoft.after.api.ExternService;
import com.isoft.after.constants.Result;
import com.isoft.after.model.dto.UserDTO;
import com.isoft.after.utils.ResponseUtil;
import org.springframework.stereotype.Service;

@Service
public class ExternServiceImpl implements ExternService {
    @Override
    public Result<UserDTO> login(String s, String s1) {
        return ResponseUtil.SUCCESS(new UserDTO().setUsername(s).setLoginType("token"));
    }
}
