package com.tpadsz.after.aop03;

import org.springframework.stereotype.Service;

/**
 * Created by hongjian.chen on 2019/1/2.
 */

@Service("mathService")
public class MathService {

    //加
    public int add(int n1, int n2) {
        int result = n1 + n2;
        System.out.println(n1 + "+" + n2 + "=" + result);
        return result;
    }

    //减
    public int subtract(int n1, int n2) {
        int result = n1 - n2;
        System.out.println(n1 + "-" + n2 + "=" + result);
        return result;
    }

    //乘
    public int mutiply(int n1, int n2) {
        int result = n1 * n2;
        System.out.println(n1 + "*" + n2 + "=" + result);
        return result;
    }

    //除
    public int divide(int n1, int n2) {
        int result = n1 / n2;
        System.out.println(n1 + "/" + n2 + "=" + result);
        return result;
    }
}
