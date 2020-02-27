package com.tpadsz.ssm.utils;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hongjian.chen on 2017/9/29.
 */
public class HttpClientUtil {

    public static String httpGet(String url, Map<String, String> para) throws Exception {

        URIBuilder builder = new URIBuilder(url);
        Set<String> set = para.keySet();
        for (String key : set) {
            builder.setParameter(key, para.get(key));
        }

        HttpGet request = new HttpGet(builder.build());
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(6000).setConnectTimeout(6000).setConnectionRequestTimeout(6000).build();
        request.setConfig(requestConfig);
        String uri = request.getURI().toString();
        System.out.println(request.getURI().toString());

        /* 1 生成 HttpClinet 对象并设置参数 */
        HttpClient httpClient = new HttpClient();
        // 设置 Http 连接超时为5秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        /* 2 生成 GetMethod 对象并设置参数 */
        GetMethod getMethod = new GetMethod(uri);
        // 设置 get 请求超时为 5 秒
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        // 设置请求重试处理，用的是默认的重试处理：请求三次
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        String response = "";
        /* 3 执行 HTTP GET 请求 */
        try {
            int statusCode = httpClient.executeMethod(getMethod);
        /* 4 判断访问的状态码 */
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("请求出错: " + getMethod.getStatusLine());
            }
        /* 5 处理 HTTP 响应内容 */
            // HTTP响应头部信息，这里简单打印
            Header[] headers = getMethod.getResponseHeaders();
//            for (Header h : headers) {
//                System.out.println(h.getName() + "------------ " + h.getValue());
//            }
            // 读取 HTTP 响应内容，这里简单打印网页内容
            byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
            response = new String(responseBody, "utf-8");
            // 读取为 InputStream，在网页内容数据量大时候推荐使用
            // InputStream response = getMethod.getResponseBodyAsStream();
        } catch (HttpException e) {
            // 发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("请检查输入的URL!");
            e.printStackTrace();
        } catch (IOException e) {
            // 发生网络异常
            System.out.println("发生网络异常!");
            e.printStackTrace();
        } finally {
        /* 6 .释放连接 */
            getMethod.releaseConnection();
        }
        return response;
    }

    public static String requestPost(String uri, String params) {
        String result = "";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(uri);
        InputStream in = null;
        BufferedReader br = null;
        try {
            RequestEntity requestEntity = new StringRequestEntity(params, "application/json", "utf-8");
            postMethod.setRequestEntity(requestEntity);
            httpClient.executeMethod(postMethod);
            in = postMethod.getResponseBodyAsStream();
            br = new BufferedReader(new InputStreamReader(in));
            String str = br.readLine();
            result = new String(str.getBytes("utf-8"), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8080/q2";

        Map<String, String> map = new HashMap();
        map.put("name", "after");
        map.put("address", "suzhou");
        String ret = httpGet(url, map);
        System.out.println("response:\n" + ret);
    }

    @Test
    public void testForm() throws Exception {
        HttpClient httpClient = new HttpClient();
        String url = "http://localhost:8080/boss-locker/maiyou/list";
        String url1 = "http://www.uichange.com/bosslocker-test/maiyou/list.json";
        String uri = "http://localhost:8080/ums3-client2/spfile/getChildrenTypes/pid4";
        String uri1 = "http://www.uichange.com/bosslocker-test/cpl/getPrizeInfo.json";
        PostMethod postMethod = new PostMethod(uri);
//   填入各个表单域的值
        NameValuePair[] data = {//
                new NameValuePair("uid", "5044a0eb21ff44e6a3f986727b69e782"), //
                new NameValuePair("dataId", "20001"), //
                new NameValuePair("type", "1"), //
                new NameValuePair("page", "1"), //
                new NameValuePair("imei", "868017029140345"),//
                new NameValuePair("cplname", "wangpai")};//
//   将表单的值放入postMethod中
//        postMethod.setRequestBody(data);
        httpClient.executeMethod(postMethod);
        InputStream in = postMethod.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String str = br.readLine();
        String result = new String(str.getBytes("utf-8"), "utf-8");
        System.out.println(result);
        br.close();
        in.close();
    }

    @Test
    public void testJson() {
        String contents = "{\"proName\":\"Plant Project Timing Test\",\"days\":26,\"itemDetail\":[{\"lightSet\":1,\"detailName\":\"Plant Project Test0\",\"sceneId\":22,\"days\":8,\"startTime\":\"6:00\",\"endTime\":\"18:00\",\"startDate\":\"2020-2-26\"},{\"lightSet\":1,\"detailName\":\"Plant Project Test1\",\"sceneId\":22,\"days\":8,\"startTime\":\"6:00\",\"endTime\":\"18:00\",\"startDate\":\"2020-3-5\"},{\"lightSet\":1,\"detailName\":\"Plant Project Test2\",\"sceneId\":22,\"days\":10,\"startTime\":\"6:00\",\"endTime\":\"18:00\",\"startDate\":\"2020-3-15\"}],\"meshId\":\"70348331\",\"itemSet\":1,\"startDate\":\"2020-2-26\",\"itemCount\":3}";

        String result = requestPost("http://localhost:8080/central-console/uploadDataFromPlant", contents);
        System.out.println(result);
    }

    @Test
    public void uploadFile() throws FileNotFoundException {
        File file = new File("C:\\temp\\tool.png");
        File file1 = new File("C:\\temp\\shopping.jpg");
        HttpClient client = new HttpClient();
        PostMethod filePost = new PostMethod("http://localhost:8080/web-ssm/file/upload2");
//       MultipartPostMethod filePost = new MultipartPostMethod(msUrl);
        // 若上传的文件比较大 , 可在此设置最大的连接超时时间
        client.getHttpConnectionManager().getParams().setConnectionTimeout(8000);
        try {
            FilePart fp = new FilePart(file.getName(), file);
            FilePart fp1 = new FilePart(file1.getName(), file1);
            StringPart sp = new StringPart("comment", "this is test");
            MultipartRequestEntity mrp = new MultipartRequestEntity(new Part[]{sp, fp, fp1}, filePost.getParams());
            filePost.setRequestEntity(mrp);
            //使用系统提供的默认的恢复策略
            filePost.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

            int httpStat = client.executeMethod(filePost);

            if (httpStat == HttpStatus.SC_OK) {
                InputStream in = filePost.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String result = new String(br.readLine().getBytes("utf-8"), "utf-8");
                System.out.println("result=" + result);
                br.close();
                in.close();
                System.out.println("UPLOAD FILE SUCCESS");
            } else {
                System.out.println("UPLOAD FILE FAILURE");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        filePost.releaseConnection();
    }
}
