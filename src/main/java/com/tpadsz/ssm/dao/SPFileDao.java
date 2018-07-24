package com.tpadsz.ssm.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by after on 2018/7/25.
 */
public interface SPFileDao {

    void insertFiles(List list);

    void insertDownloads(List list) throws Exception;
}
