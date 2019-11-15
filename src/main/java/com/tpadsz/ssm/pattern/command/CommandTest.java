package com.tpadsz.ssm.pattern.command;

/**
 * @author hongjian.chen
 * @date 2019/11/15 17:27
 */
public class CommandTest {

    public static void main(String[] args) {
        Command command1=new PlaneCommand();
        Command command2=new TrainCommand();
        DriverInvoke driverInvoke = new DriverInvoke();
        driverInvoke.setPlaneCommand(command1);
        driverInvoke.setTrainCommand(command2);
        driverInvoke.ByPlane();
        driverInvoke.ByTrain();
    }
}
