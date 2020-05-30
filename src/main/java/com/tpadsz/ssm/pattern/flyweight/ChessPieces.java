package com.tpadsz.ssm.pattern.flyweight;

import java.awt.*;

/**
 * Created by after on 2020/5/24.
 */

//抽象享元角色：棋子
public interface ChessPieces {
    void DownPieces(Graphics g, Point pt);
}
