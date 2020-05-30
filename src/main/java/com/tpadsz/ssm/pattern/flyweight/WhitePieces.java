package com.tpadsz.ssm.pattern.flyweight;

import java.awt.*;

/**
 * Created by after on 2020/5/24.
 */
public class WhitePieces implements ChessPieces {
    public void DownPieces(Graphics g, Point pt) {
        g.setColor(Color.WHITE);
        g.fillOval(pt.x, pt.y, 30, 30);
    }
}
