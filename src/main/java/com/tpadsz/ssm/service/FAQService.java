package com.tpadsz.ssm.service;



import com.tpadsz.ssm.model.FAQ;

import java.util.List;

/**
 * Created by hongjian.chen on 2018/4/27.
 */
public interface FAQService {

    List<FAQ> selectByKey(String keyword) throws Exception;

    List<String> getAllKey();
}
