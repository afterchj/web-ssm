package com.tpadsz.ssm.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2019/1/11.
 */

@Service("faqManager")
public class FAQManager {

    @Resource
    private SqlSessionTemplate sessionTemplate;

    public List<Map> getMonth(Map map) {
        List<Map> list = sessionTemplate.selectList("record.perMonth", map);
        return list;
    }

    public List<Map> getYear(Map map) {
        List<Map> list = sessionTemplate.selectList("record.perYear", map);
        return list;
    }

    public List<Map<String, String>> getYearIncome() {
        List<Map<String, String>> list = sessionTemplate.selectList("record.yearIncome");
        return list;
    }

    public List<Map<String, String>> getYearExpend() {
        List<Map<String, String>> list = sessionTemplate.selectList("record.yearExpend");
        return list;
    }

    public List<Map> getMonthIncome(Map map) {
        List<Map> list = sessionTemplate.selectList("record.monthIncome", map);
        return list;
    }

    public List<Map> getMonthExpend(Map map) {
        List<Map> list = sessionTemplate.selectList("record.monthExpend", map);
        return list;
    }

    private List<Map> initMap(List<Map> sourceMap) {
        for (int i = 0; i < sourceMap.size(); i++) {
            Map map = sourceMap.get(i);
            Iterator<Map.Entry> entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = entries.next();
                System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
            }
        }
        return sourceMap;
    }
}
