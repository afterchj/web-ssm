package com.tpadsz.ssm.model.genericity;

import com.alibaba.fastjson.JSON;
import com.mchange.v1.util.ArrayUtils;
import com.tpadsz.ssm.model.Shop;

import java.util.*;

/**
 * @author hongjian.chen
 * @date 2019/8/16 11:44
 */
public class News {
    public static <K, V> Map<K, V> map() {
        return new HashMap();
    }

    public static <T> List<T> list() {
        return new ArrayList<>();
    }

    public static <T> List<T> list(T... t) {
        List<T> list = new ArrayList<>();
        for (T item : t) {
            list.add(item);
        }
        return list;
    }

    public static void main(String[] args) {
        Map<String, List<Shop>> lmap = News.map();
        List<Shop> list = News.list();
        for (int i = 0; i < 3; i++) {
            Shop shop = new Shop();
            shop.setPid(i);
            shop.setMid("mid_" + i);
            list.add(shop);
        }
        Shop[] shops= list.toArray(new Shop[list.size()]);
        List<Shop> shopList=News.list(shops);
        lmap.put("list", list);
        lmap.put("shopList", shopList);
        System.out.println(JSON.toJSONString(lmap));
        System.out.println(JSON.toJSONString(list));
    }
}
