package com.tpadsz.ssm.utils;

import com.google.gson.Gson;
import com.tpadsz.ssm.model.User;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisShardInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hongjian.chen on 2018/1/29.
 */
public class RedisUtils {

    private static RedisTemplate redisTemplate = new RedisTemplate();

    public static RedisTemplate getRedisTemplate() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("127.0.0.1");
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.setDatabase(0);
        jedisConnectionFactory.setShardInfo(new JedisShardInfo("127.0.0.1"));
        jedisConnectionFactory.setUsePool(false);
        System.out.println(jedisConnectionFactory.getConnection().dbSize());
        jedisConnectionFactory.afterPropertiesSet();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.afterPropertiesSet();
//        redisTemplate.delete("ctc-status-004-user1");

        return redisTemplate;
    }

    public static void main(String[] args) {
        Gson gson = new Gson();

        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.delete("userHash");
        HashOperations operations = redisTemplate.opsForHash();
        User user1 = new User();
        user1.setId(101l);
        user1.setUserName("admin");
        User user2 = new User();
        user2.setId(102l);
        user2.setUserPwd("after");
        Map<String, User> map1 = new HashMap();
        map1.put("user1", user1);
        Map<String, User> map2 = new HashMap();
        map2.put("user2", user2);
        String str1 = gson.toJson(map1);
        String str2 = gson.toJson(map2);

        operations.put("userHash", "user1", str1);
        operations.put("userHash", "user2", str2);

        String user01 = String.valueOf(operations.get("userHash", "user1"));
        System.out.println("user01=" + user01);
        String user02 = String.valueOf(operations.get("userHash", "user2"));
        System.out.println("user02=" + user02);
    }
}
