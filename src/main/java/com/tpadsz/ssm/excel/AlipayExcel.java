package com.tpadsz.ssm.excel;

import com.tpadsz.ssm.dao.FAQDao;
import com.tpadsz.ssm.model.*;
import com.tpadsz.ssm.utils.MybatisUtil;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.ibatis.session.SqlSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hongjian.chen on 2017/10/24.
 */
public class AlipayExcel {
    private static SqlSession session = MybatisUtil.getSession();

    public static List<AlipayRecord> importExcel(InputStream is) throws Exception {
        List<AlipayRecord> list = new ArrayList();
        Workbook rwb = Workbook.getWorkbook(is);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy HH:mm");
        //获取Excel表的Sheet1区域的数据
        Sheet sht = rwb.getSheet(0);
        int col = sht.getColumns(); //获得Excel列
        int row = sht.getRows(); //获得Excel行
//        System.out.println("col=" + col);
//        System.out.println("row=" + row);
        System.out.println(sht.getCell(0, 5).getContents() + "\t" + sht.getCell(15, 5).getContents());
//        System.out.println("crate_time=" + sht.getCell(2, 5).getContents() + ",modify_time=" + sht.getCell(4, 5).getContents() + ",money=" + sht.getCell(9, 5).getContents());
//        System.out.println("crate_time=" + format.parse(sht.getCell(2, 7).getContents()) + ",modify_time=" + format.parse(sht.getCell(4, 7).getContents()) + ",money=" + Double.parseDouble(sht.getCell(9, 5).getContents()));
        for (int i = 5; i < row - 7; i++) {
            AlipayRecord record = new AlipayRecord();
            String createTime = sht.getCell(2, i).getContents();
            String modifyTime = sht.getCell(4, i).getContents();
            if (!createTime.isEmpty()) {
                record.setCreate_time(format.parse(createTime));
            }
            if (!modifyTime.isEmpty()) {
                record.setModify_time(format.parse(modifyTime));
            }
            record.setAccount("766256898@qq.com");
            record.setTrade_sources(sht.getCell(5, i).getContents());
            record.setTrade_description(sht.getCell(6, i).getContents());
            record.setCounterparty(sht.getCell(7, i).getContents());
            record.setShop_name(sht.getCell(8, i).getContents());
            record.setMoney(Double.parseDouble(sht.getCell(9, i).getContents()));
            record.setTrade_type(sht.getCell(10, i).getContents());
            record.setTrade_status(sht.getCell(11, i).getContents());
            record.setNotes(sht.getCell(14, i).getContents());
            record.setMoney_change(sht.getCell(15, i).getContents());
            list.add(record);
        }
        session.getMapper(FAQDao.class).insertPayRecord(list);
        return list;
    }

    public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream(new File("D:/mnt/alipay_record_2018.xls"));
        InputStream is2 = new FileInputStream(new File("D:/mnt/alipay_record_2017.xls"));
        InputStream is3 = new FileInputStream(new File("D:/mnt/alipay_record_2016.xls"));
        List<AlipayRecord> list = importExcel(is);
        List<AlipayRecord> list2 = importExcel(is2);
        List<AlipayRecord> list3 = importExcel(is3);
        System.out.println("row1="+list.size());
        System.out.println("row2="+list2.size());
        System.out.println("row3="+list3.size());
    }
}
