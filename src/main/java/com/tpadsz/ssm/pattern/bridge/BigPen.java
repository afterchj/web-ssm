package com.tpadsz.ssm.pattern.bridge;

/**
 * @author hongjian.chen
 * @date 2020/2/27 15:52
 */
public class BigPen extends Pen {
    @Override
    public void draw(String name) {
        {
            String penType = "大号毛笔绘制";
            this.color.bePaint(penType, name);
        }
    }
}
