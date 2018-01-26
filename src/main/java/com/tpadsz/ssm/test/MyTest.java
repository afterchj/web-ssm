package com.tpadsz.ssm.test;

import com.tpadsz.ssm.dao.SPItemDao;
import com.tpadsz.ssm.model.SPItem;
import com.tpadsz.ssm.model.User;
import com.tpadsz.ssm.utils.XMemcachedClient;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by hongjian.chen on 2018/1/11.
 */
public class MyTest {

    static SqlSessionFactory sessionFactory;
    static MemcachedClient memcachedClient;

    static {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        sessionFactory = (SqlSessionFactory) ac.getBean("sqlSessionFactory");
        memcachedClient = (MemcachedClient) ac.getBean("memcachedClient");
    }

    public static SqlSession getSession() {
        return sessionFactory.openSession();
    }

    @Test
    public void test1() {
        SqlSession session = getSession();
        List<SPItem> parent = session.getMapper(SPItemDao.class).getParentTypes();
        List<SPItem> children = session.getMapper(SPItemDao.class).getSubTypesById(8);
        System.out.println(parent.size() + "    " + children.size());

        for (SPItem item : children) {
            System.out.println(item.getParentId() + "   " + item.getId() + "  " + item.getName());
        }
        System.out.println(getSession());
    }

    //    public static void main(String[] args) {
//        System.out.println(getSession());
//    }
    @Test
    public void test2() {
        User user = new User();
        user.setId(101l);
        user.setCreateTime(new Date());
        user.setUserEmail("admin@qq.com");
        user.setUserName("管理员");
        user.setUserPwd("admin");

        List<User> list = new ArrayList();
        list.add(user);


        try {
            memcachedClient.set("testList", 36000, list);

            List<User> get = memcachedClient.get("testList");
            for (User u : get) {
                System.out.println(u.getId() + "\n" + u.getUserName() + "\n" + u.getUserPwd() + "\n" + u.getCreateTime() + "\n" + u.getUserEmail());
            }
//            System.out.println(memcachedClient.get("testList"));

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }

    public static MemcachedClient getMemcachedClient() {
        return memcachedClient;
    }
}
