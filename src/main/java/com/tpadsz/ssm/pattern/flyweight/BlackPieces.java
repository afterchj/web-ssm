package com.tpadsz.ssm.pattern.flyweight;

import java.awt.*;

/**
 * Created by after on 2020/5/24.
 */
public class BlackPieces implements ChessPieces {
    public void DownPieces(Graphics g, Point pt) {
        g.setColor(Color.BLACK);
        g.fillOval(pt.x, pt.y, 30, 30);
    }
}
