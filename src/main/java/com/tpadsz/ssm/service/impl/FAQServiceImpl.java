package com.tpadsz.ssm.service.impl;


import com.tpadsz.ssm.dao.FAQDao;
import com.tpadsz.ssm.model.FAQ;
import com.tpadsz.ssm.service.FAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hongjian.chen on 2018/4/27.
 */
@Service
public class FAQServiceImpl implements FAQService {

    @Autowired
    FAQDao faqDao;

    @Override
    public List<FAQ> selectByKey(String keyword)throws Exception {
        return faqDao.selectByKey(keyword);
    }

    @Override
    public List<String> getAllKey() {
        return faqDao.getAllKey();
    }
}
