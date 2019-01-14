package com;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by after on 2019/1/13.
 */
public class TempTest {

    public static void main(String[] args) {
        Object obj = "test";
        String str = "test";
        Map<String, Integer> map = new HashMap();

        for (int i = 1; i < 6; i++) {
            map.put("test" + i, i);
        }
        //遍历map中的键
        for (String key : map.keySet()) {
            System.out.println("key = " + key);
        }
        //遍历map中的值
        for (Integer value : map.values()) {
            System.out.println("value = " + value);
        }

        System.out.println(map.size() + "\t" + str.equals(obj) + "\t" + obj.equals(str));
    }
}
