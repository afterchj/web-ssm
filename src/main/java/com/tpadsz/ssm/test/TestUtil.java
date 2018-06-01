package com.tpadsz.ssm.test;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.gson.JsonArray;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by after on 2018/5/15.
 */
public class TestUtil {
    @Test
    public void test1() {
        List<Map<String, Object>> listItems = createlist();
        for (int i = 0; i < listItems.size(); i++) {
            String jsonStr = JSONUtils.toJSONString(listItems.get(i));
            System.out.println(jsonStr);
        }
    }

    public static List<Map<String, Object>> createlist() {
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
}
