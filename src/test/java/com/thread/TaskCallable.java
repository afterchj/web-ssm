package com.thread;

import java.util.concurrent.Callable;

/**
 * Created by after on 2019/1/13.
 */
public class TaskCallable implements Callable<String> {
    private int start;

    public TaskCallable(int start) {
        this.start = start;
    }

    @Override
    public String call() throws Exception {
//        System.out.println("子线程" + Thread.currentThread().getName() + "在进行计算");
//        Thread.sleep(1000);
        int sum = 0;
        for (int i = 0; i < start; i++) {
            sum += i;
        }
        return Thread.currentThread().getName() + "计算结果=" + sum;
    }
}
