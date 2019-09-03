package com.tpadsz.ssm.utils;

import com.tpadsz.ssm.rabbit.MessageProducer;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hongjian.chen on 2017/10/24.
 */
public class SpringUtils {
    private static ClassPathXmlApplicationContext ctx;

    static {
        ctx = new ClassPathXmlApplicationContext("conf/myapplicationContext.xml");
    }


    public static SqlSession getSession() {
        SqlSessionFactory factory = (SqlSessionFactory) ctx.getBean("sqlSessionFactory");
        return factory.openSession();
    }

    public static SqlSessionTemplate getSqlSession() {
        return (SqlSessionTemplate) ctx.getBean("sqlSessionTemplate");
    }

    public static AmqpTemplate getAmqpTemplate() {
        return (AmqpTemplate) ctx.getBean("amqpTemplate");
    }

    public static MessageProducer getProducer() {
        return ctx.getBean(MessageProducer.class);
    }

    //    public static void main(String[] args) {
//        Map<String,Object> map=getSession().getMapper(UserDao.class).getUserById(3);
//        for (Map.Entry<String,Object> entry:map.entrySet()){
//            System.out.println("key="+entry.getKey()+",value="+entry.getValue());
//        }
//    }
    public static void main(String[] args) {
        System.out.println(getSqlSession().selectList("com.tpadsz.ssm.dao.UserDao.selectAllUser").size());
    }
}
