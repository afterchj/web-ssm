package com.tpadsz.ssm.pattern.command;

/**
 * @author hongjian.chen
 * @date 2019/11/15 17:21
 */
public class TrainCommand implements Command {

    private Driver driver;

    public TrainCommand() {
       driver=new TrainDriver();
    }

    @Override
    public void execute() {
        driver.action();
    }
}
