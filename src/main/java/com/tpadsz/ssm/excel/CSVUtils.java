package com.tpadsz.ssm.excel;

import com.tpadsz.ssm.dao.FAQDao;
import com.tpadsz.ssm.model.AlipayRecord;
import com.tpadsz.ssm.utils.AppUtils;
import com.tpadsz.ssm.utils.SpringUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongjian.chen on 2019/1/10.
 */
public class CSVUtils {
    /**
     * 导出
     *
     * @param file     csv文件(路径+文件名)，csv文件不存在会自动创建
     * @param dataList 数据
     * @return
     */

    private static SqlSession session = SpringUtils.getSession();

    public static boolean exportCsv(File file, List<String> dataList) {
        boolean isSuccess;
        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(osw);
            if (dataList != null && !dataList.isEmpty()) {
                for (String data : dataList) {
                    bw.append(data).append("\r");
                }
            }
            isSuccess = true;
        } catch (Exception e) {
            isSuccess = false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }

    /**
     * 导入
     *
     * @param file csv文件(路径+文件)
     * @return
     */
    public static List<AlipayRecord> importCsv(String file) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<AlipayRecord> dataList = new ArrayList();
        BufferedReader br = null;
        DataInputStream in;
        String account = "766256898@qq.com";
        try {
            in = new DataInputStream(new FileInputStream(file));
            br = new BufferedReader(new InputStreamReader(in, "GBK"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] contents = line.split(",");
                if (contents.length == 1 && line.contains("账号")) {
                    String str = contents[0];
                    account = str.substring(str.indexOf("[") + 1, str.length() - 1);
                    System.out.println("str=" + str + ",account=" + account);
                }
                AlipayRecord record = new AlipayRecord();
                record.setAccount(account);
                if (contents.length == 16 && !line.contains("交易号 ")) {
                    String createTime = contents[2];
                    String modifyTime = contents[4];
                    record.setCreate_time(createTime);
                    record.setModify_time(modifyTime);
//                    if (!createTime.isEmpty()) {
//                        record.setCreate_time(format.parse(createTime));
//                    }
//                    if (!modifyTime.isEmpty()) {
//                        record.setModify_time(format.parse(modifyTime));
//                    }
                    record.setTrade_sources(contents[5]);
                    record.setTrade_description(contents[6]);
                    record.setCounterparty(contents[7]);
                    record.setShop_name(contents[8]);
                    record.setMoney(Double.parseDouble(contents[9]));
                    record.setTrade_type(contents[10]);
                    record.setTrade_status(contents[11]);
                    record.setNotes(contents[14]);
                    record.setMoney_change(contents[15]);
                    dataList.add(record);
                } else {
                    System.out.println("length=" + contents.length + "，" + line);
                }
            }
            session.getMapper(FAQDao.class).insertPayRecord(dataList);
            AppUtils.getSession().setAttribute("account", account);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataList;
    }

    /**
     * CSV导出
     *
     * @throws Exception
     */
    @Test
    public void exportCsv() {
        List<String> dataList = new ArrayList();
        dataList.add("1,张三,男");
        dataList.add("2,李四,男");
        dataList.add("3,小红,女");
        boolean isSuccess = exportCsv(new File("D:/temp/demo.csv"), dataList);
        System.out.println(isSuccess);
    }

    /**
     * CSV导出
     *
     * @throws Exception
     */

    @Test
    public void importCsv() {
        String file06 = "D:/mnt/alipay_record_20190109_1642_1.csv";
        String file08 = "D:/mnt/alipay_record_20190109_1418_1.csv";
        String file09 = "D:/dev/alipay_record_20210425_2140_1.csv";
        importCsv(file09);
    }
}
