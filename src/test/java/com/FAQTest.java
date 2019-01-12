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
        Map map=new HashMap();
        map.put("startTime","2018-01-01");
        map.put("endTime","2018-01-01");
        FAQManager faqManager = (FAQManager) ctx.getBean("faqManager");
        List<Map> list= faqManager.getMonthIncome(map);
        List<Map> list1 = faqManager.getMonthExpend(map);
        System.out.println(JSON.toJSONString(list).replace(" ", ""));
        System.out.println(JSON.toJSONString(list1).replace(" ", ""));
    }

    @Test
    public void testMonth() {
        List<Map> list = getSession().selectList("record.perMonth");
        System.out.println(JSON.toJSONString(list).replace(" ", ""));
        Map map = list.get(0);
        Iterator<Map.Entry> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    @Test
    public void testYear() {
        List<Map<String,String>> list = getSession().selectList("record.perYear");
//       for (int i=0;i<list.size();i++){
//           Map<String, Object> map = list.get(i);
//           for (Map.Entry<String, Object> entry : map.entrySet()) {
//               if (entry.getKey().equals("total")){
//                map.put("total", entry.getValue().toString());
//               }
//           }
//       }
        System.out.println(JSON.toJSONString(list).replace(" ", ""));
    }

    @Test
    public void testYearIncome() {
        List<Map> list = getSession().selectList("record.yearIncome");
        Map<String, Object> map = list.get(0);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }
        System.out.println(JSON.toJSONString(list).replace(" ", ""));
    }

    @Test
    public void testMonthExpend() {
        List<Map> list = getSession().selectList("record.monthExpend");
//        Map<String, Object> map = list.get(0);
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
//        }
        System.out.println(JSON.toJSONString(list).replace(" ", ""));
    }
}
