package com.tpadsz.ssm.test;

/**
 * Created by after on 2018/5/11.
 */
public enum Color {
    RED(1, "红色"), GREEN(2, "绿色"), YELLOW(4, "黄色");
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    Color(int index, String name) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (Color c : Color.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return this.index + "_" + this.name;
    }
}
