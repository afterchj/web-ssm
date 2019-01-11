package com.tpadsz.ssm.utils;

import org.apache.log4j.Logger;

import java.io.File;


public class ZipUtils {

    private static Logger logger = Logger.getLogger(ZipUtils.class);


    public static void main(String[] args) {
        String targetPath = "D:/temp";
//        File file = new File("D:\\work\\web-ssm\\target\\artifacts\\web_ssm_war_exploded\\upload\\demo.zip");
//        System.out.println(file.delete());
//        FileUtils.unZipFiles(file, targetPath, true);

        File file1 = new File("D:\\mnt\\csv.zip");
        FileUtils.unZipFiles(file1, targetPath, true);
        logger.info("文件解压成功！");
//      compressFiles("E:\\exda.zip", "E:\\");
    }
}