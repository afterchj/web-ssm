package com.tpadsz.ssm.test;

import java.util.Scanner;

/**
 * Created by hongjian.chen on 2018/7/30.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int a=(int)(Math.random()*100);
        int b=(int)(Math.random()*100);
        int c=a+b;
        int d=scan.nextInt();
        System.out.println(a);
        System.out.println(b);
        System.out.println();
        int score=0;
        for(int i=1;i<11;i++){
            if(d==c){
                score=score+10;
                System.out.println("恭喜您答对了+10分，现在得分:"+score);
                d=scan.nextInt();
//                continue;
            }else {
                System.out.println("哎呀，算错了");
                d=scan.nextInt();
            }


        }

    }
}
