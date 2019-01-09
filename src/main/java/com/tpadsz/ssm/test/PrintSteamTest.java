package com.tpadsz.ssm.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by after on 2018/11/4.
 */
public class PrintSteamTest {

    public static void main(String[] args) {
        System.out.println("show result:");
        try {
            FileOutputStream fos = new FileOutputStream("demo.txt");
            PrintStream ps = new PrintStream(fos);
            ps.println("普通字符串");
            ps.println(new PrintSteamTest());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
