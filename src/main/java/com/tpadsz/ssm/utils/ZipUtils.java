package com.tpadsz.ssm.utils;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.util.*;


public class ZipUtils {

    private static Logger logger = Logger.getLogger(ZipUtils.class);


    @SuppressWarnings("unchecked")
    public static List<File> unZipFiles(File file, String fileSavePath, boolean isDelete) throws Exception {
        if ((!file.exists()) && (file.length() <= 0)) {
            logger.error("要解压的文件不存在!");
            return null;
        }
        List<File> files = new ArrayList<>();
        String sep = File.separator;
        String subDir, fileName;
        ZipFile zipFile = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            //一定要加上编码，之前解压另外一个文件，没有加上编码导致不能解压
            zipFile = new ZipFile(file, "gbk");
            Enumeration<ZipEntry> e = zipFile.getEntries();
            while (e.hasMoreElements()) {
                ZipEntry zipEnt = e.nextElement();
                subDir = zipEnt.getName();
                fileName = fileSavePath + sep + subDir;
                File newFile = new File(fileName);
                if (zipEnt.isDirectory()) { //目录
                    File dir = new File(fileName);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
//                    continue;
                } else {
                    // 读写文件
                    InputStream is = zipFile.getInputStream(zipEnt);
                    bis = new BufferedInputStream(is);
                    // 建目录
//                    String subDir = filePath;
                    String temp="";
                    for (int i = 0; i < subDir.length(); i++) {
                        String str = subDir.substring(i, i + 1);
                        if (str.equalsIgnoreCase("/") || str.equalsIgnoreCase(sep)) {
                            temp = fileSavePath + sep + subDir.substring(0, i);
                            File subdir = new File(temp);
                            if (!subdir.exists()) {
                                subdir.mkdir();
                            }
                            System.out.println("suffix=" + subDir+",\tdirectory=" + temp);
                        }
                    }
                    System.out.println("fileName=" + fileName);
                    fos = new FileOutputStream(fileName);
                    bos = new BufferedOutputStream(fos);
                    int len;
                    byte[] buff = new byte[1024];
                    while ((len = bis.read(buff)) != -1) {
                        bos.write(buff, 0, len);
                    }
                    files.add(newFile);
                }
                System.out.println("--------------------------------------------分割线--------------------------------------------");
            }
        } catch (Exception e) {
            logger.error("解压文件出现异常：", e);
        } finally {
            zipFile.close();
            bis.close();
            bos.close();
            fos.close();
        }
        if (isDelete) {
            boolean flag = file.delete();
            logger.info("删除源文件结果: " + flag);
        }
        logger.debug("compress files success");
        return files;
    }

    public static void main(String[] args) throws Exception {
        String targetPath = "D:/temp";
//        File file = new File("C:/mnt/alipay_record_20190109_1642.zip");
//        unZipFiles(file, targetPath, false);
        File file1 = new File("C:\\temp\\test.zip");
        unZipFiles(file1, targetPath, false);
//      compressFiles("E:\\exda.zip", "E:\\");
    }
}