package com.tpadsz.ssm.test;

import com.tpadsz.ssm.dao.SPItemDao;
import com.tpadsz.ssm.model.SPItem;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by hongjian.chen on 2018/1/11.
 */
public class MyTest {

    static SqlSessionFactory sessionFactory;

    static {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        sessionFactory = (SqlSessionFactory) ac.getBean("sqlSessionFactory");
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
}
