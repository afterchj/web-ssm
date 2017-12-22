package com.heitian.ssm.utils;

import java.io.File;

/**
 * Created by hongjian.chen on 2017/12/22.
 */
public class FileDemo {

        public static void main(String[] args){
            File f1 =new File("c:\\test");
            System.out.println("f1="+f1);
            //File(String parent,String child)
            File f2 =new File("c:\\test","2.txt");
            System.out.println("f2="+f2);
            File f3 =new File("c:"+File.separator+"test");
            System.out.println("f3="+f3);
            //File(File parent,String child)
            File f4 =new File(f3,"3.txt");
            System.out.println("f4="+f4);
        }

    }
