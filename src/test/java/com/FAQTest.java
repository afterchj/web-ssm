package com;

import com.alibaba.fastjson.JSON;
import com.tpadsz.ssm.model.User;
import com.tpadsz.ssm.service.FAQManager;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2019/1/11.
 */
public class FAQTest {

    private static ApplicationContext ctx;

    static {
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    public static SqlSessionTemplate getSession() {
        return (SqlSessionTemplate) ctx.getBean("sqlSessionTemplate");
    }

    @Test
    public void test() {
        SqlSessionTemplate sqlSessionTemplate = getSession();
        List<User> list = sqlSessionTemplate.selectList("com.tpadsz.ssm.dao.UserDao.selectAllUser");
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void testFaq() {
        FAQManager faqManager = (FAQManager) ctx.getBean("faqManager");
        Map map = new HashMap();
        map.put("startTime", "2018-01-01");
        map.put("endTime", "2018-01-01");
        List<Map> list = faqManager.getMonth(map);
        map.put("startTime", "2015-01-01");
        map.put("endTime", "2015-01-01");
        List<Map> list1 = faqManager.getMonth(map);
        System.out.println(list.size());
        System.out.println(list1.size());
    }

    @Test
    public void testMonth() {
        Map map = new HashMap();
        map.put("startTime", "2016-01-01");
        map.put("endTime", "2016-01-01");
        List<Map> list = getSession().selectList("record.perMonth", map);
        System.out.println("perMonth;" + list.size());
        each(list);
    }

    @Test
    public void testYear() {
        List<Map<String, Object>> list = getSession().selectList("record.perYear");
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue().toString().contains("支付")) {
                    System.out.println("value=" + entry.getValue());
                } else {
                    System.out.println("key=" + entry.getKey());
                }
            }
        }
//        System.out.println(JSON.toJSONString(list).replace(" ", ""));
    }

    @Test
    public void testYearIncome() {
        Map map=new HashMap();

        List<Map> list = getSession().selectList("record.yearIncome");

        System.out.println(JSON.toJSONString(list).replace(" ", ""));
    }

    @Test
    public void testMonthExpend() {
        Map map = new HashMap();
        map.put("startTime", "2015-01-01");
        map.put("endTime", "2015-01-01");
        List<Map> list = getSession().selectList("record.monthIncome",map);
//        Map<String, Object> map = list.get(0);
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
//        }
        System.out.println(JSON.toJSONString(list).replace(" ", ""));
    }

    public void each(List<Map> list) {
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            Iterator<Map.Entry> entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = entries.next();
                if (entry.getKey().toString().contains("支付")) {
                    System.out.println("key" + entry.getKey() + ",value=" + entry.getValue());
                }
            }
        }
    }
}