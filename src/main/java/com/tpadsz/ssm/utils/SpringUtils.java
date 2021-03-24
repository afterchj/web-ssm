package com.tpadsz.ssm.utils;

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


}
