package com.tpadsz.ssm.service.impl;


import com.tpadsz.ssm.dao.SPItemDao;
import com.tpadsz.ssm.model.SPItem;
import com.tpadsz.ssm.service.SPItemService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hongjian.chen on 2018/1/11.
 */
public class SPItemServiceImpl implements SPItemService {

    @Resource
    SPItemDao spItemDao;

    public List<SPItem> getSubTypesById(int id) {
        return spItemDao.getSubTypesById(id);
    }

    public List<SPItem> getSubTypes() {
        return spItemDao.getSubTypes();
    }

    public List<SPItem> getParentTypes() {
        return spItemDao.getParentTypes();
    }
}
