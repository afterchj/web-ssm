package com.tpadsz.ssm.model;

/**
 * Created by hongjian.chen on 2017/10/24.
 * 对应black_list数据库 list_black_cause表
 */
public class DListCause {
    private int id;
    private String listId;
    private String cause;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
