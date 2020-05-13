package com.tpadsz.ssm.pattern.command;

/**
 * @author hongjian.chen
 * @date 2019/11/15 17:37
 */
public class DriverInvoke {

    private Command planeCommand;
    private Command trainCommand;

    public void setPlaneCommand(Command planeCommand) {
        this.planeCommand = planeCommand;
    }

    public void setTrainCommand(Command trainCommand) {
        this.trainCommand = trainCommand;
    }

    public void ByPlane() {
        planeCommand.execute();
    }
    public void ByTrain() {
        trainCommand.execute();
    }
}
