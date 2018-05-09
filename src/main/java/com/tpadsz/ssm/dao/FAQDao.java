package com.tpadsz.ssm.dao;



import com.tpadsz.ssm.model.FAQ;

import java.util.List;

/**
 * Created by hongjian.chen on 2018/4/26.
 */
public interface FAQDao {
    void insertBatch(List<FAQ> list);

    List<FAQ> selectByKey(String keyword) throws Exception;

    List<String> getAllKey();
}
