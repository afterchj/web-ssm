package com.tpadsz.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.tpadsz.ssm.service.FAQManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2019/1/11.
 */

@RestController
@RequestMapping("/charts")
public class FAQController {

    @Autowired
    private FAQManager faqManager;


    @RequestMapping("/perYear")
    public List<Map> getYearRecord(String startTime, String endTime) {
        System.out.println("start=" + startTime + ",end=" + endTime);
        Map map = new HashMap();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        List<Map> list = faqManager.getYear(map);
        return list;
    }

    @RequestMapping("/perMonth")
    public List<Map> getMonthRecord(String startTime, String endTime) {
        System.out.println("start=" + startTime + ",end=" + endTime);
        Map map = new HashMap();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        List<Map> list = faqManager.getMonth(map);
        return list;

    }

    @RequestMapping("/showYearIncome")
    public List<Map<String, String>> yearIncome() {
        List<Map<String, String>> list = faqManager.getYearIncome();
//        System.out.println(JSON.toJSONString(list));
        return list;
    }

    @RequestMapping("/showYearExpend")
    public List<Map<String, String>> yearExpend() {
        return faqManager.getYearExpend();
    }

    @RequestMapping("/showMonthIncome")
    public List<Map> monthIncome() {
        Map map = new HashMap();
        map.put("startTime", "2018-01-01");
        map.put("endTime", "2018-01-01");
        return faqManager.getMonthIncome(map);
    }

    @RequestMapping("/showMonthExpend")
    public List<Map> monthExpend() {
        Map map = new HashMap();
        map.put("startTime", "2018-01-01");
        map.put("endTime", "2018-01-01");
        return faqManager.getMonthExpend(map);
    }
}
