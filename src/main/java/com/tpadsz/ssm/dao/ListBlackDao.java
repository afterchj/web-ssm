package com.tpadsz.ssm.dao;

import com.tpadsz.ssm.model.DListBlack;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ListBlackDao {
    //设定初始时间查询黑名单用户
    List<DListBlack> listBlacksByTime(@Param("startTime") Date startTime);

    //通过主键_id查询此黑名单用户
    DListBlack findBlackById(DListBlack dListBlack);

    //插入一条黑名单用户
    void insertOneBlack(DListBlack dListBlack);

}
