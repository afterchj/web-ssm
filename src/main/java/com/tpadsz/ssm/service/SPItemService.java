package com.tpadsz.ssm.service;


import com.tpadsz.ssm.model.SPItem;

import java.util.List;

/**
 * Created by hongjian.chen on 2018/1/11.
 */
public interface SPItemService {
    List<SPItem> getSubTypesById(int id);

    List<SPItem> getSubTypes();

    List<SPItem> getParentTypes();
}
