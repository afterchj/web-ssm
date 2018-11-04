package com.tpadsz.ssm.test;

/**
 * Created by after on 2018/11/4.
 */
public class Main {
    public static void main(String[] args) {
        MyDemo demo=(str,a,b)->str.substring(a,b);
        MyDemo demo1=String::substring;
        String str=demo1.test("I love you,java",5,13);
        System.out.println(str);

    }
}
