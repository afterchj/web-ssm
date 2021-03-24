package com.tpadsz.ssm.pattern.bridge;

/**
 * @author hongjian.chen
 * @date 2020/2/27 15:55
 */
public class Green implements Color {
    public void bePaint(String penType, String name) {
        System.out.println(penType + "绿色的" + name + ".");
    }
}