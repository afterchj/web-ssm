package com.tpadsz.ssm.utils;


import com.tpadsz.ssm.dao.FAQDao;
import com.tpadsz.ssm.model.FAQ;
import com.tpadsz.ssm.model.Shop;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import org.apache.ibatis.session.SqlSession;

import java.io.*;
import java.util.*;


/**
 * Created by hongjian.chen on 2017/10/24.
 */
public class ExcelTool {

    private static SqlSession session = MybatisUtil.getSession();

    public static void importExcel() {

        List<FAQ> list3 = session.getMapper(FAQDao.class).getAll();
        System.out.println(list3.size());
        WritableWorkbook book = null;
        try {
            book = Workbook.createWorkbook(new File("D:/" + "c.xls"));
            WritableSheet sheet = book.createSheet("test", 0);
            sheet.addCell(new Label(0, 0, "编号"));
            sheet.addCell(new Label(1, 0, "问题"));
            sheet.addCell(new Label(2, 0, "答案"));
            for (int i = 0; i < list3.size(); i++) {
                sheet.addCell(new Number(0, i + 1, list3.get(i).getId()));
                sheet.addCell(new Label(1, i + 1, list3.get(i).getQuestion()));
                sheet.addCell(new Label(2, i + 1, list3.get(i).getAnswer()));
            }
            book.write();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                book.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Shop> exporpExcel() throws IOException, BiffException, WriteException {
        List<Shop> list = new ArrayList();
        String filePath = "D:\\pid.xls";
        InputStream is = null;
        Workbook rwb = null;

        try {
            is = new FileInputStream(filePath);//定义文本输入流
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            rwb = Workbook.getWorkbook(is);//打开Workbook
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取Excel表的Sheet1区域的数据
        Sheet sht = rwb.getSheet(0);
        int col = sht.getColumns(); //获得Excel列
        int row = sht.getRows(); //获得Excel行
        System.out.println("row=" + row);

        //先将数据按行装入一个一维数组中， 然后将数组逐个加入到ArrayList
        for (int i = 0; i < row; i++) {
//            FAQ faq = new FAQ();
            Shop faq=new Shop();
            for (int j = 0; j < col; j++) {
                faq.setPid(Integer.parseInt(sht.getCell(0, i).getContents()));
                faq.setMid(sht.getCell(1, i).getContents());
//                faq.setUrl(sht.getCell(2, i).getContents());
//                faq.setAnswer(sht.getCell(3, i).getContents());
//                faq.setKeyword(sht.getCell(4, i).getContents());
            }
            list.add(faq);
        }
        return list;
    }

    public static List<String> getAllKey() {
        List<String> list = session.getMapper(FAQDao.class).getAllKey();
        for (int i = 0; i < 6; i++) {
            System.out.println(list.get(i));
        }
        return list;
    }

    public static List<FAQ> selectByKey() throws Exception {
        Map map = new HashMap();
        map.put("keyword", "%奖金%");
        return session.getMapper(FAQDao.class).selectByKey("奖金");
    }

    public static void main(String[] args) throws Exception {
//        importExcel();
        System.out.println(selectByKey().size());
//        System.out.println(session.getMapper(FAQDao.class).getAllKey());
//        InputStream is = new FileInputStream(new File("D:/test/example.xls"));
//        importExcel(is);
        List<Shop> list = exporpExcel();
        session.getMapper(FAQDao.class).insertShop(list);
    }
}
