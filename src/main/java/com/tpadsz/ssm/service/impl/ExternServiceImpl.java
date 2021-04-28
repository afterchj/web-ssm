package com.tpadsz.ssm.service.impl;

import com.isoft.after.api.ExternService;
import com.isoft.after.constants.CommonCodeEnum;
import com.isoft.after.exception.BaseException;
import com.isoft.after.model.dto.UserDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ExternServiceImpl implements ExternService {
    @Override
    public UserDTO login(String s, String s1){
        if (StringUtils.isEmpty(s) || StringUtils.isEmpty(s1)) {
            throw new BaseException(CommonCodeEnum.NULL_ARGUMENT);
        }
        return new UserDTO().setUsername(s).setLoginType("token");
    }
}
