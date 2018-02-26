package com.tpadsz.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hongjian.chen on 2017/12/18.
 */
public class CpaAndGameLog implements Serializable {
    private int id;
    private String taskType;
    private Date logDate;
    private int newUser;
    private int taskUser;
    private int taskTotal;
    private double expendSum;

    private String dataId;
    private String dataName;
    private int total;

    private String downloadUrl;
    private double totalExpend;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public int getNewUser() {
        return newUser;
    }

    public void setNewUser(int newUser) {
        this.newUser = newUser;
    }

    public int getTaskUser() {
        return taskUser;
    }

    public void setTaskUser(int taskUser) {
        this.taskUser = taskUser;
    }

    public int getTaskTotal() {
        return taskTotal;
    }

    public void setTaskTotal(int taskTotal) {
        this.taskTotal = taskTotal;
    }

    public double getExpendSum() {
        return expendSum;
    }

    public void setExpendSum(double expendSum) {
        this.expendSum = expendSum;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public double getTotalExpend() {
        return totalExpend;
    }

    public void setTotalExpend(double totalExpend) {
        this.totalExpend = totalExpend;
    }
}
