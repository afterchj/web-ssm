package com.tpadsz.ssm.model;

import java.util.Date;

/**
 * Created by hongjian.chen on 2019/1/9.
 */
public class AlipayRecord {
    private int id;
    private String account;
    private String create_time;
    private String modify_time;
    private String trade_sources;
    private String trade_description;
    private String counterparty;//交易对方
    private String shop_name;
    private double money;//金额
    private String trade_type;
    private String trade_status;
    private String notes;
    private String money_change;//资金状态

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public String getTrade_sources() {
        return trade_sources;
    }

    public void setTrade_sources(String trade_sources) {
        this.trade_sources = trade_sources;
    }

    public String getTrade_description() {
        return trade_description;
    }

    public void setTrade_description(String trade_description) {
        this.trade_description = trade_description;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getMoney_change() {
        return money_change;
    }

    public void setMoney_change(String money_change) {
        this.money_change = money_change;
    }
}
