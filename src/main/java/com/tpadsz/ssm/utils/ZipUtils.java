package com.tpadsz.ssm.utils;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class ZipUtils {

    private static Logger logger = Logger.getLogger(ZipUtils.class);


    @SuppressWarnings("unchecked")
    public static Map<String,Object> unZipFiles(String zipFilePath, String fileSavePath, boolean isDelete) throws Exception {
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            File f = new File(zipFilePath);
            if ((!f.exists()) && (f.length() <= 0)) {
                throw new RuntimeException("要解压的文件不存在!");
            }
            //一定要加上编码，之前解压另外一个文件，没有加上编码导致不能解压
            ZipFile zipFile = new ZipFile(f, "gbk");
            String strPath, filePath, fileName;
            strPath = fileSavePath;// 输出的绝对位置
            Enumeration<ZipEntry> e = zipFile.getEntries();
            while (e.hasMoreElements()) {
                org.apache.tools.zip.ZipEntry zipEnt = e.nextElement();
                filePath = zipEnt.getName();
                fileName = strPath + File.separator + filePath;
                if (zipEnt.isDirectory()) { //目录
                    File dir = new File(fileName);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    continue;
                } else {
                    // 读写文件
                    InputStream is = zipFile.getInputStream(zipEnt);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    // 建目录
                    String strsubdir = filePath;
                    for (int i = 0; i < strsubdir.length(); i++) {
                        if (strsubdir.substring(i, i + 1).equalsIgnoreCase("/")) {
                            String temp = strPath + File.separator + strsubdir.substring(0, i);
                            File subdir = new File(temp);
                            if (!subdir.exists())
                                subdir.mkdir();
                            System.out.println("filePath="+filePath+",fileName="+fileName);
                        }
                    }
                    FileOutputStream fos = new FileOutputStream(fileName);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    int len;
                    byte[] buff = new byte[1024];
                    while ((len = bis.read(buff)) != -1) {
                        bos.write(buff, 0, len);
                    }
                    bos.close();
                    fos.close();
                }
                System.out.println();
            }
            zipFile.close();
        } catch (Exception e) {
            logger.error("解压文件出现异常：", e);
            throw e;
        }
        if (isDelete) {
            boolean flag = new File(zipFilePath).delete();
            logger.debug("删除源文件结果: " + flag);
        }
        logger.debug("compress files success");
        return map;
    }

    public static void main(String[] args) throws Exception {
        unZipFiles("D:/temp/test.zip", "D:/test/", false);
//      unZipFiles("E:\\exda.zip", "E:\\", true);
//      compressFiles("E:\\exda.zip", "E:\\");
    }
}