package com.tpadsz.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 对应black_list数据库 list_black表
 */
public class DListBlack implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Integer typeId;
    private String appId;
    private Date expireDate;
    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "DListBlack{" +
                "id='" + id + '\'' +
                ", typeId=" + typeId +
                ", appId='" + appId + '\'' +
                ", expireDate=" + expireDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
