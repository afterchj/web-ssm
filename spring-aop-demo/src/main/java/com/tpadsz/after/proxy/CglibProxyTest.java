package com.tpadsz.after.proxy;

import org.junit.Test;

/**
 * Created by hongjian.chen on 2019/1/22.
 */
public class CglibProxyTest {

    @Test
    public void test() {
        //目标对象
        Singer target = new Singer();
        //代理对象
        Singer proxy = (Singer) new ProxyFactory(target).getProxyInstance();
        //执行代理对象的方法
        String name = proxy.sing("I for you");
        System.out.println("result=" + name);
    }
}
