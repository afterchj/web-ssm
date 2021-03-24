package com.tpadsz.ssm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author hongjian.chen
 * @date 2019/8/16 14:51
 */
public class AiMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;
        while (true) {
            str = scanner.next();
            str = str.replace("吗", "");
            str = str.replace("谁", "小可爱");
            str = str.replace("你", "我");
            str = str.replace("？", "!");
            str = str.replace("?", "!");
            System.out.println(str);
        }
    }
}
