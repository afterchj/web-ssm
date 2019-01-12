package com.tpadsz.ssm.service;

import com.tpadsz.ssm.dao.FAQDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
