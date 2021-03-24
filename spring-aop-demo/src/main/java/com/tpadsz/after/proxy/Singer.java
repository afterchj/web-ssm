package com.tpadsz.after.proxy;

/**
 * Created by hongjian.chen on 2019/1/22.
 */
public class Singer {
    public String sing(String name) {
        System.out.println("唱一首歌：" + name);
        return "[" + name + "]";
    }
}
