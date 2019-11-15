package com.tpadsz.ssm.pattern.command;

/**
 * @author hongjian.chen
 * @date 2019/11/15 17:21
 */
public class PlaneCommand implements Command {

    private Driver driver;

    public PlaneCommand() {
       driver=new PlaneDriver();
    }

    @Override
    public void execute() {
        driver.action();
    }
}
