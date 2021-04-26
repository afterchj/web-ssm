package com.tpadsz.ssm.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by after on 2019/1/13.
 */
public class AppUtils {

    private static Logger logger = LoggerFactory.getLogger(AppUtils.class);

    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
            logger.error("初始化session失败！" + e.getMessage());
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
     *
     * @param request 请求实体对象
     * @return ip 外网ip<br/>
     */
    public static String getRemoteIP(HttpServletRequest request) {
        request = request == null ? getRequest() : request;
        assert request != null;
        String ip = request.getHeader("x-forwarded-for");
        logger.warn("x-forwarded-for {}", ip);
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            logger.warn("Proxy-Client-IP {}", ip);
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            logger.warn("WL-Proxy-Client-IP {}", ip);
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
