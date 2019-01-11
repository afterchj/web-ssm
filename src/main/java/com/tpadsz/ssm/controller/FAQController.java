package com.tpadsz.ssm.controller;

import com.tpadsz.ssm.service.FAQManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2019/1/11.
 */

@Controller
@RequestMapping("/charts")
public class FAQController {

    @Autowired
    private FAQManager faqManager;

    @RequestMapping("/perMonth")
    public void getRecord() {

    }

    @ResponseBody
    @RequestMapping("/showYearExpend")
    public List<Map> yearIncome() {
        return faqManager.getYearExpend();
    }
}
