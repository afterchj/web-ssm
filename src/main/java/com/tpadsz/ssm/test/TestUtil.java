package com.tpadsz.ssm.test;

import com.alibaba.fastjson.JSON;
import com.tpadsz.ssm.dao.SPFileDao;
import com.tpadsz.ssm.dao.UserDao;
import com.tpadsz.ssm.utils.SpringUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

/**
 * Created by after on 2018/5/15.
 */
public class TestUtil {
    @Test
    public void test1() {
        Random random=new Random();
        for (int i=0;i<100;i++){
            System.out.println(random.nextInt(2000)+1);
        }
//        List<Map<String, Object>> listItems = createList();
//        for (int i = 0; i < listItems.size(); i++) {
//            String jsonStr = JSONUtils.toJSONString(listItems.get(i));
//            System.out.println(jsonStr);
//        }
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
    public void testUpdateByMap() {

        SqlSession session = SpringUtils.getSession();
        String str = "{\"id\":1,\"user_name\":\"管理员\",\"create_time\":\"2018-07-18\",\"is_delete\":1}";

        Map<String, Object> map = JSON.parseObject(str, Map.class);
        session.getMapper(UserDao.class).updateUser(map);

        Map<String, Object> map1 = session.getMapper(UserDao.class).getUserById(1);
        for (Map.Entry<String, Object> entry : map1.entrySet()) {
            System.out.println("key=" + entry.getKey() + ",value=" + entry.getValue());
        }
    }

    @Test
    public void testInsertFile() {
        SqlSession session = SpringUtils.getSession();
        Long current = System.currentTimeMillis();
        List<Map> list = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            Map map = new HashMap();
            map.put("name", "视频" + i);
            map.put("description", "test" + i);
            list.add(map);
        }

        session.getMapper(SPFileDao.class).insertFiles(list);
        System.out.println("插入时间：" + (System.currentTimeMillis() - current / 1000));
    }

    @Test
    public void testInsertDownload() {

        SqlSession session = SpringUtils.getSession();
        Long current = System.currentTimeMillis();
        List<Map> list = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            Map map = new HashMap();
            map.put("file_id", i + 5);
            map.put("total_download", i);
            list.add(map);
        }

        try {
            session.getMapper(SPFileDao.class).insertDownloads(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("插入时间：" + (System.currentTimeMillis() - current));
    }

    @Test
    public void testInsertSubSelect() {
        Random random=new Random();
        SqlSession session = SpringUtils.getSession();
        for (int i=0;i<100;i++){
            Map map = new HashMap();
            map.put("id",random.nextInt(2000)+1);
            map.put("total_download", i+1);
            session.getMapper(SPFileDao.class).insertSubSelect(map);
        }
    }
}
