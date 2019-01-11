package com.tpadsz.ssm.utils;

import com.tpadsz.ssm.excel.AlipayExcel;
import com.tpadsz.ssm.excel.CSVUtils;
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by hongjian.chen on 2019/1/11.
 */
public class FileUtils {

    private static Logger logger = Logger.getLogger(FileUtils.class);

    public static boolean saveFile(MultipartFile file, String savePath, String fileName, boolean flag) {
        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        File targetFile = new File(savePath, fileName);
        System.out.println("file=" + targetFile.getPath());
        try {
            if (!file.isEmpty()) {
                file.transferTo(targetFile);
                if (flag && suffix.equals(".zip")) {
                    List<File> files = unZipFiles(targetFile, savePath, flag);
                    System.out.println("文件解压位置=" + files.get(0).getPath());
                }
                return true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public static List<File> unZipFiles(File file, String fileSavePath, boolean isDelete) {
        if ((!file.exists()) && (file.length() <= 0)) {
            logger.error("要解压的文件不存在!");
            return null;
        }
        List<File> files = new ArrayList<>();
        String sep = File.separator;
        String fileName;
        String subDir;
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
                    String temp = "";
                    for (int i = 0; i < subDir.length(); i++) {
                        String str = subDir.substring(i, i + 1);
                        if (str.equalsIgnoreCase("/") || str.equalsIgnoreCase(sep)) {
                            temp = fileSavePath + sep + subDir.substring(0, i);
                            File subdir = new File(temp);
                            if (!subdir.exists()) {
                                subdir.mkdir();
                            }
                            System.out.println("suffix=" + subDir + ",\tdirectory=" + temp);
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
                    if (isDelete) {
                        new JobThread(fileName).start();
                    }
                    files.add(newFile);
                }
                System.out.println("--------------------------------------------分割线--------------------------------------------");
            }
        } catch (IOException e) {
            logger.error("解压文件出现异常：", e);
        } finally {
            try {
                bis.close();
                bos.close();
                fos.close();
                zipFile.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            if (isDelete) {
                logger.info("删除源文件结果: " + file.delete());
            }
        }
        logger.debug("compress files success");
        return files;
    }

    public static void importFile(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        System.out.println("suffix=" + suffix);
        if (suffix.equals(".xls")) {
            AlipayExcel.importExcel(fileName);
        } else if (suffix.equals(".csv")) {
            CSVUtils.importCsv(fileName);
        } else {
            return;
        }
    }

    private static class JobThread extends Thread {

        private List<File> files;
        String fileName;

        //单文件导入
        public JobThread(String fileName) {
            this.fileName = fileName;
        }

        //执行多个文件导入
        public JobThread(List<File> files) {
            this.files = files;
        }

        @Override
        public void run() {
            importFile(fileName);
//            for (File file : files) {
//                importFile(file.getPath());
//            }
        }
    }
}
