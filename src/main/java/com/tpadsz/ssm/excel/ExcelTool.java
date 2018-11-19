package com.tpadsz.ssm.excel;

import com.tpadsz.ssm.dao.CpaAndGameDao;
import com.tpadsz.ssm.dao.ListBlackDao;
import com.tpadsz.ssm.dao.ListCauseDao;
import com.tpadsz.ssm.model.CpaAndGameLog;
import com.tpadsz.ssm.model.DListBlack;
import com.tpadsz.ssm.model.DListCause;
import com.tpadsz.ssm.utils.MybatisUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
import org.apache.ibatis.session.SqlSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hongjian.chen on 2017/10/24.
 */
public class ExcelTool {
    private static SqlSession session = MybatisUtil.getSession();

    public static void importExcel(InputStream is) throws Exception {
        Workbook book = null;
        String reason;
        try {
            book = Workbook.getWorkbook(is);
            Sheet sheet = book.getSheet(0);
            int rows = sheet.getRows();
            System.out.println("rows=" + rows);
            for (int i = 1; i < rows; i++) {
                DListBlack black = new DListBlack();
                DListCause cause = new DListCause();
                Cell[] cell = sheet.getRow(i);
                reason = cell[cell.length - 1].getContents();
                int len = cell.length;
                black.setId(cell[0].getContents());
                black.setTypeId(1);
                black.setAppId("9");
                black.setUpdateDate(new Date());
                cause.setListId(cell[0].getContents());
                cause.setCause(reason);
                session.getMapper(ListBlackDao.class).insertOneBlack(black);
                session.getMapper(ListCauseDao.class).saveOne(cause);
                if (len == 2 || len == 3) {
                    DListBlack black2 = new DListBlack();
                    DListCause cause2 = new DListCause();
                    black2.setId(cell[1].getContents());
                    black2.setTypeId(2);
                    black2.setAppId("9");
                    black2.setUpdateDate(new Date());
                    cause2.setListId(cell[1].getContents());
                    cause2.setCause(reason);
                    session.getMapper(ListBlackDao.class).insertOneBlack(black2);
                    session.getMapper(ListCauseDao.class).saveOne(cause2);
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (book != null) {
                book.close();
            }
            if (session != null) {
                session.close();
            }
        }
    }

    public static void exportExcel(Map map, String fileName) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        List<CpaAndGameLog> list3 = session.getMapper(CpaAndGameDao.class).getDataInfo(map);
        WritableWorkbook book = null;
        try {
            book = Workbook.createWorkbook(new File(fileName));
            WritableSheet sheet = book.createSheet("test", 0);
            sheet.addCell(new Label(0, 0, "任务日期"));
            sheet.addCell(new Label(1, 0, "任务名称"));
            sheet.addCell(new Label(2, 0, "费用支出"));
            for (int i = 0; i < list3.size(); i++) {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(list3.get(i).getLogDate()) + "\t" + list3.get(i).getTaskType() + "\t" + list3.get(i).getExpendSum());
                sheet.addCell(new Label(0, i + 1, new SimpleDateFormat("yyyy-MM-dd").format(list3.get(i).getLogDate())));
                sheet.addCell(new Label(1, i + 1, list3.get(i).getTaskType()));
                sheet.addCell(new Number(2, i + 1, list3.get(i).getExpendSum()));
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

    public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream(new File("D:/test/example.xls"));
        importExcel(is);
        Map map = new HashMap();
        map.put("type", "新快速");
        exportExcel(map, "D:\\mnt\\info.xls");
    }
}
