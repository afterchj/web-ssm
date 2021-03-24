package com.tpadsz.ssm.utils;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyUtil {
    private static ApplicationContext ctx;

    static {
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    private static RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");


    @Test
    public void testPut() {
        HashOperations opsForHash = redisTemplate.opsForHash();
        opsForHash.put("he1", "key1", "a");
        opsForHash.put("he1", "key2", "b");
        opsForHash.put("he1", "key3", "c");
        Map<String, Object> entries = opsForHash.entries("he1");
        System.out.println(entries);//{key3=c, key1=a, key2=b}(无序)
    }

    @Test
    public void testMap() {
        Map map = new HashMap();
        map.put("id", "123aw21sad231a232313");
        map.put("uid", "aw2121xwwq12121s121");
        map.put("type", "2");
        map.put("imsi", "112121212");
        map.put("createDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        redisTemplate.opsForHash().putAll("mapRecord", map);

        Map<String, String> resultMap = redisTemplate.opsForHash().entries("mapRecord");

        List<String> reslutMapValues = redisTemplate.opsForHash().values("mapRecord");

        Set<String> resultMapKeys = redisTemplate.opsForHash().keys("mapRecord");

        String value = (String) redisTemplate.opsForHash().get("mapRecord", "createDate");
        System.out.println("value:" + value);

        System.out.println("resultMapKeys:" + resultMapKeys);
        System.out.println("resultMap:" + resultMap);
        System.out.println("reslutMapValues:" + reslutMapValues);
    }

    @Test
    public void testSet() {
        Set<String> set1 = new HashSet<String>();
        set1.add("set1");
        set1.add("set2");
        set1.add("set3");
        redisTemplate.opsForSet().add("set1", set1);
        Set<String> resultSet = redisTemplate.opsForSet().members("set1");
        System.out.println("resultSet:" + resultSet);
    }

    @Test
    public void testList() {
        List<String> list1 = new ArrayList<String>();
        list1.add("a1");
        list1.add("a2");
        list1.add("a3");

        List<String> list2 = new ArrayList<String>();
        list2.add("b1");
        list2.add("b2");
        list2.add("b3");
        redisTemplate.opsForList().leftPush("lisKey1", list1);
        redisTemplate.opsForList().rightPush("listKey2", list2);

        List<String> resultList1 = (List<String>) redisTemplate.opsForList().leftPop("lisKey1");

        List<String> resultList2 = (List<String>) redisTemplate.opsForList().rightPop("listKey2");
        System.out.println("resultList1:" + resultList1);
        System.out.println("resultList2:" + resultList2);
    }

    @Test
    public void test() {

        ValueOperations opsForValue = redisTemplate.opsForValue();
        String key="uic_cache_alipay_times_8bd036f28c934b7ca9a428adf06e6f66";
//        redisTemplate.opsForValue().set(key, "1");
        String times = (String) redisTemplate.opsForValue().get(key);
        System.out.println("times="+times);
        opsForValue.set("mapRecord", "121");
        System.out.println(opsForValue.get("mapRecord"));
//        redisTemplate.opsForList().remove("framework", 3, "spring");
//        redisTemplate.opsForList().leftPush("testList", "solr");
        redisTemplate.opsForList().leftPush("testList", "list测试");
        List list = redisTemplate.opsForList().range("testList", 0, -1);
        for (Object str : list) {
            System.out.println("object=" + str);
        }
//        System.out.println(redisTemplate.hasKey("homgjian"));
    }
}
