package com.tpadsz.ssm.test;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.google.gson.JsonArray;
import com.tpadsz.ssm.dao.UserDao;
import com.tpadsz.ssm.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

/**
 * Created by after on 2018/5/15.
 */
public class TestUtil {
    @Test
    public void test1() {
        List<Map<String, Object>> listItems = createList();
        for (int i = 0; i < listItems.size(); i++) {
            String jsonStr = JSONUtils.toJSONString(listItems.get(i));
            System.out.println(jsonStr);
        }
    }

    public static List<Map<String, Object>> createList() {
        int[] imageIds = new int[]{5, 6, 7, 8, 9, 10};
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("image", imageIds[i]);
            listItems.add(listItem);
            System.out.println(listItem.size());
        }
        return listItems;
    }

    @Test
    public void testUpdateByMap(){

        SqlSession session= MybatisUtil.getSession();
        String str = "{\"id\":1,\"user_name\":\"管理员\",\"create_time\":\"2018-07-18\",\"is_delete\":1}";

        Map<String,Object> map=JSON.parseObject(str,Map.class);
        session.getMapper(UserDao.class).updateUser(map);

        Map<String,Object> map1=session.getMapper(UserDao.class).getUserById(1);
        for (Map.Entry<String,Object> entry:map1.entrySet()){
            System.out.println("key="+entry.getKey()+",value="+entry.getValue());
        }
    }
}
