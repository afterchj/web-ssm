package com.tpadsz.ssm.pattern.bridge;

/**
 * @author hongjian.chen
 * @date 2020/2/27 15:57
 */
public class BridgeClient {
    public static void main(String a[]) {
        Color color;
        Pen pen;

        color = (Color) XMLUtilPen.getBean("color");
        pen = (Pen) XMLUtilPen.getBean("pen");

        pen.setColor(color);
        pen.draw("鲜花");
    }
}
