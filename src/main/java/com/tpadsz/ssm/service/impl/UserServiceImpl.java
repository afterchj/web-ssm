package com.tpadsz.ssm.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpadsz.ssm.dao.UserDao;
import com.tpadsz.ssm.model.User;
import com.tpadsz.ssm.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by hjchen on 2016/7/15.
 */

@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public User getUserById(Long userId) {
        return userDao.selectUserById(userId);
    }

    public User getUserByPhoneOrEmail(String emailOrPhone, Short state) {
        return userDao.selectUserByPhoneOrEmail(emailOrPhone,state);
    }

    public List<Map> getAllUser() {
        PageHelper.startPage(1,10);
        List<Map> list=userDao.selectAllUser();
        PageInfo<Map> pageInfo=new PageInfo<>(list);
        System.out.println(pageInfo.getPageSize());
//        System.out.println(JSON.toJSON(pageInfo));
        return pageInfo.getList();
    }

    public User selectByName(User user) {
        return userDao.selectByName(user);
    }

}
