package com.tpadsz.ssm.pattern.bridge;

/**
 * @author hongjian.chen
 * @date 2020/2/27 16:04
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Test.class.getResource(""));
        System.out.println(Test.class.getResource("/conf/configPen.xml").getFile());
        System.out.println(Test.class.getClassLoader().getResource(""));
        System.out.println(Test.class.getClassLoader().getResource("/"));
    }
}
