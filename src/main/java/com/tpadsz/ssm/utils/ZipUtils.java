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
        String file = "D:\\temp\\alipay_record_20190112_1513.zip";
        String file2 = "D:\\test\\test.zip";
        File file1 = new File(file);
        FileUtils.uZipFiles(file1, targetPath, true);
        logger.info("文件解压成功！");
//      compressFiles("E:\\exda.zip", "E:\\");
    }
}