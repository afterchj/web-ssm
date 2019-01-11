package com.tpadsz.ssm.excel;

import com.alibaba.fastjson.JSON;
import com.tpadsz.ssm.dao.FAQDao;
import com.tpadsz.ssm.model.AlipayRecord;
import com.tpadsz.ssm.utils.MybatisUtil;
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

    private static SqlSession session = MybatisUtil.getSession();

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
        try {
            in = new DataInputStream(new FileInputStream(file));
            br = new BufferedReader(new InputStreamReader(in, "GBK"));
            String line;
            String account = "";
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
                    if (!createTime.isEmpty()) {
                        record.setCreate_time(format.parse(createTime));
                    }
                    if (!modifyTime.isEmpty()) {
                        record.setModify_time(format.parse(modifyTime));
                    }
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
//        session.getMapper(FAQDao.class).insertPayRecord(dataList);
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
        String file07 = "D:/mnt/alipay_record_20190109_1409_1.csv";
        List<AlipayRecord> dataList1 = importCsv(file06);
        List<AlipayRecord> dataList2 = importCsv(file07);
        List<AlipayRecord> dataList3 = importCsv(file08);
        System.out.println("row1=" + dataList1.size());
        System.out.println("row2=" + dataList2.size());
        System.out.println("row3=" + dataList3.size());
//        if (dataList != null && !dataList.isEmpty()) {
//            for (int i = 10; i < 15; i++) {
//                System.out.println(JSON.toJSONString(dataList.get(i)));
//            }
//        }
    }

    public static void main(String[] args) {

    }
}
