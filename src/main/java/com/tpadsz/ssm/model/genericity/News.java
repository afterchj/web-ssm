package com.tpadsz.ssm.model.genericity;

import com.alibaba.fastjson.JSON;
import com.tpadsz.ssm.model.Shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hongjian.chen
 * @date 2019/8/16 11:44
 */
public class News {
    public static <K, V> Map<K, V> map() {
        return new HashMap();
    }

    public static <T> List<T> list(T... t) {
        List<T> list = new ArrayList<>();
        for (T item : t) {
            list.add(item);
        }
        return list;
    }

//    public static <T> List<T> list(T... t) {
//        List<T> list = new ArrayList<>();
//        for (T item : t) {
//            list.add(item);
//        }
//        return list;
//    }

    public static void main(String[] args) {
        Map<String, List<Shop>> lmap = News.map();
        List<Shop> list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            Shop shop = new Shop();
            shop.setPid(i);
            shop.setMid("mid_" + i);
            list.add(shop);
        }
        lmap.put("shop", list);
        System.out.println(JSON.toJSONString(lmap));
    }
}
