package com.tpadsz.ssm.pattern.command;

/**
 * @author hongjian.chen
 * @date 2019/11/15 17:24
 */
public class PlaneDriver implements Driver {

    @Override
    public void action() {
        System.out.println("By plane...");
    }
}
