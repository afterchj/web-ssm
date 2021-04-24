package com.tpadsz.ssm.utils;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * 补充
 *
 * @author chenjz
 * @since 2020-12-17 11:31
 */

public class IpQuery {

private static Logger log= LoggerFactory.getLogger(IpQuery.class);
    public static void getMobileMarkFromIP138(String mobile) {

        String REQUEST_URL = "https://ip138.com/mobile.asp?mobile=" + mobile + "&action=mobile";
        //请求方法
        String REQUEST_MOTHOD = "GET";
        //提取手机号码归属地的正则表达式
        //编译后的正则表达式Pattern对象
        BufferedReader br = null;
        try {
            HttpURLConnection httpConn = (HttpURLConnection) new URL(REQUEST_URL).openConnection();
            httpConn.setRequestMethod(REQUEST_MOTHOD);
            httpConn.setDoOutput(false);
            httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4128.3 Safari/537.36");
            httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            httpConn.setRequestProperty("Host", "ip138.com");
            httpConn.setRequestProperty("Cookie", "Hm_lvt_f4f76646cd877e538aa1fbbdf351c548=1606180596,1608171527,1608194777; Hm_lpvt_f4f76646cd877e538aa1fbbdf351c548=1608194777; Hm_lvt_ecdd6f3afaa488ece3938bcdbb89e8da=1606187389,1608183300,1608194785; Hm_lpvt_ecdd6f3afaa488ece3938bcdbb89e8da=1608194785; ASPSESSIONIDCAAQDSQB=LEIFJKFDDOADPBLDCIHDJKFC");
            br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String s;
            StringBuffer sb = new StringBuffer();
            while ((s = br.readLine()) != null) {
                sb.append(s + "\n");
            }
            log.warn("text {}", sb.toString());
            br.close();
        } catch (IOException e) {
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
    }

    public static void getMobileInfo(String mobile) {
        String url = "https://ip138.com/mobile.asp?mobile=" + mobile + "&action=mobile";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String text = "";
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4128.3 Safari/537.36");
            httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            httpGet.addHeader("Host", "ip138.com");
            httpGet.addHeader("Cookie", "Hm_lvt_f4f76646cd877e538aa1fbbdf351c548=1606180596,1608171527,1608194777; Hm_lpvt_f4f76646cd877e538aa1fbbdf351c548=1608194777; Hm_lvt_ecdd6f3afaa488ece3938bcdbb89e8da=1606187389,1608183300,1608194785; Hm_lpvt_ecdd6f3afaa488ece3938bcdbb89e8da=1608194785; ASPSESSIONIDCAAQDSQB=LEIFJKFDDOADPBLDCIHDJKFC");
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    text = new String(EntityUtils.toString(entity).getBytes("ISO-8859-1"), "utf-8");
                }
                log.warn("text {}", text);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static JSONObject buildMobileInfo(String text) {
        JSONObject mobileInfo = new JSONObject();
        Document doc = Jsoup.parse(text);
        Element element = doc.select("div div").select(".table").first();
        // 获取按钮的文本
        Element tbody = element.getElementsByTag("tbody").first();
        Elements rows = tbody.getElementsByTag("tr");
        for (int i = 0; i < rows.size(); i++) {
            Element td = rows.get(i).getElementsByTag("td").last();
            String str = td.text();
            if (StringUtils.isNotEmpty(str)) {
                if (i == 0) {
                    mobileInfo.put("mobile", str.substring(0, str.indexOf(" ")));
                }
                if (i == 1) {
                    if (StringUtils.isNotEmpty(str)) {
                        String[] location = str.split(" ");
                        mobileInfo.put("province", location[0]);
                        mobileInfo.put("city", location[1]);
                    }
                }
                if (i == 2) {
                    mobileInfo.put("operater", str);
                }
            }

        }
        return mobileInfo;
    }

    public static List<String> generateMobile(int size) {
//        String str = "150 151 152 155 157 158 159 182 183 187 188";
//        String str = "132 134 135 136 137 138 139 147 150 151 152 155 157 158 159 182 183 187 188";
        String str = "182 183 187 188";
        String[] prefix = str.split(" ");
        List<String> mobiles = new ArrayList<>();
        for (int i = 0; i < prefix.length; i++) {
            for (int j = 0; j < size; j++) {
                StringBuffer buffer = new StringBuffer(prefix[i]);
                if (j < 10) {
                    buffer.append("000" + j);
                }
                if (j >= 10 && j < 100) {
                    buffer.append("00" + j);
                }
                if (j >= 100 && j < 1000) {
                    buffer.append("0" + j);
                } else if (j >= 1000) {
                    buffer.append(j);
                }
                buffer.append("8888");
                mobiles.add(buffer.toString());
//                log.warn("mobile {} length {}", buffer.toString(), buffer.length());
            }
        }
        return mobiles;
    }


    public static void subList(List list, int count) {
        int begin = 0;
        int end = begin + count;
        while (begin <= list.size() - 1) {
            List subList = list.subList(begin, end <= list.size() ? end : list.size());
            System.out.println("index :" + begin + "\t" + end);
            begin = end;
            end = begin + count;
        }

    }

    public static void main(String[] args) {

        generateMobile(1);


//        MobileUtils.MobileInfo info = MobileUtils.sendGetMobileInfo("18170756879");
//        MobileInfoEntity entity = JSON.parseObject(JSON.toJSONString(info), MobileInfoEntity.class);
//        List<String> mobiles = generateMobile();
//        subList(mobiles, 500);
//        List<FMobileInfo> mobileInfos = buildMobileInfo(mobiles);
//        log.warn("mobiles {} mobileInfos {}", mobiles.size(), mobileInfos.size());
//        log.warn("size {}", generateModel().size());
//        FMobileInfo info = getMobileMarkFromIP138("18170756879");
//        log.warn("info {} \n entity {}", JSON.toJSONString(info), entity);

    }

}
