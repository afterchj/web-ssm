package com.tpadsz.ssm.test;

/**
 * Created by after on 2018/5/11.
 */
public class EnumTest {
    public static void main(String[] args) {
        Color color1=Color.YELLOW;
        System.out.println(color1.toString());
        for (Color color : Color.values()) {
            System.out.println(color.getIndex()+"\t"+color.getName());
        }
    }
}
