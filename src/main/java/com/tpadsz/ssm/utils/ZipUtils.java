package com.tpadsz.ssm.utils;

import org.apache.log4j.Logger;

import java.io.File;


public class ZipUtils {

    private static Logger logger = Logger.getLogger(ZipUtils.class);


    public static void main(String[] args) {
        String targetPath = "D:/temp";
//        File file = new File("D:\\work\\web-ssm\\target\\artifacts\\web_ssm_war_exploded\\upload\\demo.zip");
//        System.out.println(file.delete());
//        FileUtils.uZipFiles(file, targetPath, true);
//        String file = "D:\\temp\\csv\\demo.zip";
        String file2 = "D:\\test\\csv.zip";
        File file1 = new File(file2);
        FileUtils.uZipFiles(file1, targetPath, false);
        logger.info("文件解压成功！");
//      compressFiles("E:\\exda.zip", "E:\\");
    }
}