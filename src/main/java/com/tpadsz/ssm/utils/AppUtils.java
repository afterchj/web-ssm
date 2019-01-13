package com.tpadsz.ssm.utils;

import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by after on 2019/1/13.
 */
public class AppUtils {

    private static Logger logger = Logger.getLogger(AppUtils.class);

    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
            System.out.println("初始化session:" + session);
        } catch (Exception e) {
            logger.error("初始化session失败！" + session);
        }
        return session;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attrs.getRequest();
        if (request != null) {
            return request;
        } else {
            logger.error("request为空！");
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(getRequest());
    }
}
