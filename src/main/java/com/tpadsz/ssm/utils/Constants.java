package com.tpadsz.ssm.utils;

/**
 * Created by hongjian.chen on 2018/8/1.
 */
public enum Constants {
    SESSION_USERNAME("USERNAME"),
    WEB_SSM("ws://122.112.229.195/web-ssm/websocket"),
    BLT_LIGHT("ws://ctc-hq.tpadsz.com/blt_light/websocket"),
    UICHANGE_BLT("ws://uichange.com/blt_light/websocket"),
    TEST_URL("ws://127.0.0.1:8080/web-ssm/websocket"),
    BLT_URL("ws://127.0.0.1:8080//ws/webSocket");

    private String username;

    Constants(String username) {
        this.username = username;
    }

    public String value() {
        return this.username;
    }

//    public static void main(String[] args) {
//        System.out.println(Constants.TEST_URL.value());
//    }

}
