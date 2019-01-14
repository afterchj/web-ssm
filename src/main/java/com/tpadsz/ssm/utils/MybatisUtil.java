package com.tpadsz.ssm.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hongjian.chen on 2017/10/24.
 */
public class MybatisUtil {
    private static ClassPathXmlApplicationContext atx;

    static {
        atx = new ClassPathXmlApplicationContext("conf/myapplicationContext.xml");
    }


    public static SqlSession getSession() {
        SqlSessionFactory factory = (SqlSessionFactory) atx.getBean("sqlSessionFactory");
        return factory.openSession();
    }


//    public static void main(String[] args) {
//        Map<String,Object> map=getSession().getMapper(UserDao.class).getUserById(3);
//        for (Map.Entry<String,Object> entry:map.entrySet()){
//            System.out.println("key="+entry.getKey()+",value="+entry.getValue());
//        }
//    }

}
