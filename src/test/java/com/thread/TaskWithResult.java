package com.thread;

import java.util.concurrent.Callable;

/**
 * Created by after on 2019/1/13.
 */
public class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
//        System.out.println(Thread.currentThread().getName() + "在执行任务！");
        //一个模拟耗时的操作
        for (int i = 99; i > 0; i--) ;
        return Thread.currentThread().getName() + "执行的结果是：" + id;
    }
}
