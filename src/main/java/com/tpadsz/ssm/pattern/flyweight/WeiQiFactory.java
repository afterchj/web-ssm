package com.tpadsz.ssm.pattern.flyweight;

import java.util.ArrayList;

/**
 * Created by after on 2020/5/24.
 */
public class WeiQiFactory {
    private ArrayList<ChessPieces> qz;

    public WeiQiFactory() {
        qz = new ArrayList<>();
        ChessPieces w = new WhitePieces();
        qz.add(w);
        ChessPieces b = new BlackPieces();
        qz.add(b);
    }

    public ChessPieces getChessPieces(String type) {
        if (type.equalsIgnoreCase("w")) {
            return qz.get(0);
        } else if (type.equalsIgnoreCase("b")) {
            return qz.get(1);
        } else {
            return null;
        }
    }
}
