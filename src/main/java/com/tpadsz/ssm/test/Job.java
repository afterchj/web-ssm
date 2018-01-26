package com.tpadsz.ssm.test;

import com.tpadsz.ssm.model.User;
import com.tpadsz.ssm.utils.XMemcachedClient;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hongjian.chen on 2018/1/25.
 */
public class Job {

    @Resource
    private XMemcachedClient client;


    public void execute() throws Exception {
        User user = new User();
        user.setId(102l);
        user.setCreateTime(new Date());
        user.setUserEmail("after@qq.com");
        user.setUserName("超级管理员");
        user.setUserPwd("after");

        List<User> list = new ArrayList();
        list.add(user);

        Map map = new HashMap();
        map.put("id", "123aw21sad231a232313");
        map.put("id", "123aw21sad231a232313");
        map.put("uid", "aw2121xwwq12121s121");
        map.put("type", "2");
        map.put("imsi", "112121212");
        map.put("createDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        System.out.println("自动执行任务时间," + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println("memcachedClient=" + client.getMemcachedClient());
        client.set("userList", 3000, list);

        List<User> get = client.get("userList");
        for (User u : get) {
            System.out.println(u.getId() + "\n" + u.getUserName() + "\n" + u.getUserPwd() + "\n" + u.getCreateTime() + "\n" + u.getUserEmail());
        }
        System.out.println(get);
//        System.out.println("testMap=" + client.get("testMap"));
    }
}
