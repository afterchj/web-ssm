package com.tpadsz.ssm.dao;


import com.tpadsz.ssm.model.SPItem;

import java.util.List;

/**
 * Created by hongjian.chen on 2018/1/11.
 */
public interface SPItemDao {

    List<SPItem> getSubTypesById(int id);
    List<SPItem> getSubTypes();
    List<SPItem> getParentTypes();
}
