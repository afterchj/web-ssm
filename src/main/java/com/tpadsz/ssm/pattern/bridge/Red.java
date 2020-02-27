package com.tpadsz.ssm.pattern.bridge;

/**
 * @author hongjian.chen
 * @date 2020/2/27 15:55
 */
public class Red implements Color {
    public void bePaint(String penType, String name) {
        System.out.println(penType + "红色的" + name + ".");
    }
}