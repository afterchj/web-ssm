package com.tpadsz.ssm.utils;

import com.tpadsz.ssm.excel.AlipayExcel;
import com.tpadsz.ssm.excel.CSVUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by hongjian.chen on 2019/1/11.
 */
public class FileUtils {

    private static Logger logger = Logger.getLogger(FileUtils.class);

    public static void saveFile(MultipartFile file, String savePath, String fileName, boolean flag) {
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        File targetFile = new File(savePath, fileName);
        try {
            if (!file.isEmpty()) {
                System.out.println("文件解压位置=" + targetFile.getPath());
                file.transferTo(targetFile);
                if (StringUtils.equals(suffix, ".xls") || StringUtils.equals(suffix, ".xlsx")) {
                    AlipayExcel.importExcel(targetFile.getPath());
                } else if (StringUtils.equals(suffix, ".csv")) {
                    CSVUtils.importCsv(targetFile.getPath());
                } else if (StringUtils.equals(suffix, ".zip")) {
                    uZipFiles(targetFile, savePath, flag);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public static void uZipFiles(File file, String fileSavePath, boolean flag) {
        if ((!file.exists()) && (file.length() <= 0)) {
            logger.warn("要解压的文件不存在!");
            return;
        }
        List<File> files = new ArrayList<>();
        String sep = File.separator;
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
                String subDir = zipEnt.getName();
                String fileName = fileSavePath + sep + subDir;
                File newFile = new File(fileName);
                if (zipEnt.isDirectory()) { //目录
                    File dir = new File(fileName);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                } else {
                    // 读写文件
                    InputStream is = zipFile.getInputStream(zipEnt);
                    bis = new BufferedInputStream(is);
                    // 建目录
                    for (int i = 0; i < subDir.length(); i++) {
                        String str = subDir.substring(i, i + 1);
                        if (str.equalsIgnoreCase("/") || str.equalsIgnoreCase(sep)) {
                            String temp = fileSavePath + sep + subDir.substring(0, i);
                            File subdir = new File(temp);
                            if (!subdir.exists()) {
                                subdir.mkdir();
                            }
//                            System.out.println("suffix=" + subDir + ",\tdirectory=" + temp);
                        }
                    }
//                    System.out.println("fileName=" + fileName);
                    fos = new FileOutputStream(fileName);
                    bos = new BufferedOutputStream(fos);
                    int len;
                    byte[] buff = new byte[1024];
                    while ((len = bis.read(buff)) != -1) {
                        bos.write(buff, 0, len);
                    }
                    importFile(fileName);
                    files.add(newFile);
                }
//                new JobThread(files).start();
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
            if (flag) {
                logger.info("删除源文件结果: " + file.delete());
//                logger.info("删除源文件结果: " + isDelete);
            }
        }
        logger.debug("compress files success");
    }

    public static void uZip(List<File> files, String savePath, boolean flag) {
        for (File file : files) {
//            File targetFile = new File(fileName);
            String str = file.getName();
            if (str.lastIndexOf(".zip") != -1) {
                System.out.println("targetFile=" + file.getPath());
                uZipFiles(file, savePath, flag);
                if (flag) {
                    logger.info("删除源文件结果: " + file.delete());
                }
            }
        }
    }

    public static void importFile(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        if (StringUtils.equals(suffix, ".xls")) {
            AlipayExcel.importExcel(fileName);
        } else if (StringUtils.equals(suffix, ".csv")) {
            CSVUtils.importCsv(fileName);
        } else {
            ExcelTool.readExcel(fileName);
        }
    }

    private static class JobThread extends Thread {

        private List<File> files;
        private String fileName;

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
            files.forEach(file -> ExcelTool.readExcel(file.getAbsolutePath()));
        }

        public static void main(String[] args) {
            File file1 = new File("E:" + File.separator + "t_student.xls");
            File file2 = new File("E:" + File.separator + "t_student_out.xls");
            new JobThread(Arrays.asList(file1, file2)).start();
        }
    }
}
