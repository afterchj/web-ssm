package com.tpadsz.ssm.excel;

import com.tpadsz.ssm.dao.FAQDao;
import com.tpadsz.ssm.model.*;
import com.tpadsz.ssm.utils.AppUtils;
import com.tpadsz.ssm.utils.SpringUtils;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hongjian.chen on 2017/10/24.
 */

@Slf4j
public class AlipayExcel {

    private static Logger logger = Logger.getLogger(AlipayExcel.class);
    private static SqlSession session = SpringUtils.getSession();

    public static List<AlipayRecord> importExcel(String fileName) {
        String account = "766256898@qq.com";
        List<AlipayRecord> list = new ArrayList();
        InputStream in = null;
        Workbook rwb = null;
        try {
            in = new FileInputStream(fileName);
            rwb = Workbook.getWorkbook(in);
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy HH:mm");
            //获取Excel表的Sheet1区域的数据
            Sheet sht = rwb.getSheet(0);
            int row = sht.getRows(); //获得Excel行
            for (int i = 0; i < row - 7; i++) {
                if (i == 1) {
                    String str = sht.getCell(0, i).getContents();
                    account = str.substring(str.indexOf("[") + 1, str.lastIndexOf("]"));
                    System.out.println("Excel列=" + sht.getColumns() + "\t" + account);
                }
                if (i >= 5) {
                    AlipayRecord record = new AlipayRecord();
                    String createTime = sht.getCell(2, i).getContents();
                    String modifyTime = sht.getCell(4, i).getContents();
                    record.setCreate_time(createTime);
                    record.setModify_time(modifyTime);
//                if (!createTime.isEmpty()) {
//                    try {
//                        if (!createTime.isEmpty()) {
//                            record.setCreate_time(format.parse(createTime));
//                        }
//                        if (!modifyTime.isEmpty()) {
//                            record.setModify_time(format.parse(modifyTime));
//                        }
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
                    record.setAccount(account);
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
            }
            session.getMapper(FAQDao.class).insertPayRecord(list);
            AppUtils.getSession().setAttribute("account=", account);
        } catch (Exception e) {
            log.error("cause {} msg {}", e.getCause(), e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (rwb != null) {
                rwb.close();
            }
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        String is = "D:/mnt/alipay_record_2018.xls";
        String is2 = "D:/mnt/alipay_record_2017.xls";
        String is3 = "D:/test/alipay_record_2016.xls";
        List<AlipayRecord> list = importExcel(is3);
//        List<AlipayRecord> list2 = importExcel(is2);
//        List<AlipayRecord> list3 = importExcel(is3);
        System.out.println("row1=" + list.size());
//        System.out.println("row2=" + list2.size());
//        System.out.println("row3=" + list3.size());
    }
}
