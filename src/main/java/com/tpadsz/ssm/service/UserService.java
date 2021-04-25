package com.tpadsz.ssm.service;

import com.tpadsz.ssm.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by hjchen on 2016/7/15.
 */
public interface UserService {

    List<Map> getAllUser();

    User getUserByPhoneOrEmail(String emailOrPhone, Short state);

    User getUserById(Long userId);

    User selectByName(User user);
}
