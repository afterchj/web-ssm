package com;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

/**
 * Created by hongjian.chen on 2018/8/1.
 */
public class LogTest {

    private static Logger logger = Logger.getLogger(LogTest.class);
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("This is println message.");
        // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");

        String deviceIds = "{\"DBD47F5FD33A464ABA32AA97D00EB1CE\",\"DBD47F5FD33A464ABA32AA97D00EB1CE\",\"B59B48A74ACB4EB8A2C181AEDFBF42A6\"}";
        try {
            JSONObject jsonObject= JSONObject.parseObject(deviceIds);
            System.out.println(jsonObject.toString());
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        String jsonString = JSON.toJSONString(deviceIds);
        System.out.println(jsonString.contains("B59B48A74ACB4EB8A2C181AEDFBF42A6"));
    }
}