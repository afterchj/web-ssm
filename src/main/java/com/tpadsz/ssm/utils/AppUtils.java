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

    /**
     * getRemoteIP:获取远程请求客户端的外网IP <br/>
     * @param request 请求实体对象
     * @return ip 外网ip<br/>
     */
    public static String getRemoteIP(HttpServletRequest request) {
        if (request == null) {
            request = getRequest();
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
