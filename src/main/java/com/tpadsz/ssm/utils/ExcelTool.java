package com.tpadsz.ssm.utils;


import com.tpadsz.ssm.dao.FAQDao;
import com.tpadsz.ssm.model.FAQ;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by hongjian.chen on 2017/10/24.
 */
public class ExcelTool {

    private static SqlSession session = SpringUtils.getSession();
    private static SqlSessionTemplate sqlSessionTemplate = SpringUtils.getSqlSession();

    public static void exportExcel() {

        List<FAQ> list3 = session.getMapper(FAQDao.class).getAll();
        System.out.println(list3.size());
        WritableWorkbook book = null;
        try {
            book = Workbook.createWorkbook(new File("D:/c.xls"));
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

    public static List importExcel() throws IOException, BiffException, WriteException {
//        List<Shop> list = new ArrayList();
        String filePath = "e:\\mobile.xls";
        InputStream is = null;
        Workbook rwb = null;
        try {
            is = new FileInputStream(filePath);//定义文本输入流
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
        List<String> list = new ArrayList();
        for (int i = 1; i < row; i++) {
//            Map map=new HashMap();

//            Integer id = Integer.parseInt(sht.getCell(0, i).getContents());
            String mobile = sht.getCell(0, i).getContents();
//            System.out.println("id=" + id);
//            FAQ faq = new FAQ();
//            Shop faq = new Shop();
//            faq.setPid(id);
//            faq.setMid(sht.getCell(1, i).getContents());
//                faq.setUrl(sht.getCell(2, i).getContents());
//                faq.setAnswer(sht.getCell(3, i).getContents());
//                faq.setKeyword(sht.getCell(4, i).getContents());
            list.add(mobile);
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
//        System.out.println(selectByKey().size());
//        System.out.println(session.getMapper(FAQDao.class).getAllKey());
//        InputStream is = new FileInputStream(new File("D:/test/example.xls"));
//        exportExcel();
        List list = importExcel();
        sqlSessionTemplate.insert("com.tpadsz.ssm.dao.FAQDao.insertMobile", list);
//        session.getMapper(FAQDao.class).deleteBatch(list);
    }
}
