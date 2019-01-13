package com;

import com.thread.TaskCallable;
import com.thread.TaskWithResult;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by after on 2019/1/13.
 */
public class CallableTest {

    @Test
    public void testFutureTask() throws ExecutionException, InterruptedException {

        List<FutureTask<Integer>> tasks = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            TaskCallable taskCallable;
            FutureTask<Integer> task;
            if (i % 2 == 0) {
                taskCallable = new TaskCallable(1000);
                task = new FutureTask(taskCallable);
                executorService.submit(task);
                tasks.add(task);
            } else if (i % 3 == 0) {
                taskCallable = new TaskCallable(100);
                task = new FutureTask(taskCallable);
                executorService.execute(task);
                tasks.add(task);
            } else {
                taskCallable = new TaskCallable(10);
                task = new FutureTask(taskCallable);
                executorService.submit(task);
                tasks.add(task);
            }
        }
        for (FutureTask<Integer> futureTask : tasks) {
            System.out.println(futureTask.get());
        }
        Thread.sleep(1000);
        executorService.shutdown();
    }


    @Test
    public void testFuture() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<String>> resultList = new ArrayList();
        //创建10个任务并执行
        for (int i = 0; i < 10; i++) {
            //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<String> future = executorService.submit(new TaskWithResult(i));
            //将任务执行结果存储到List中
            resultList.add(future);
        }
        //遍历任务的结果
        for (Future<String> fs : resultList) {
            try {
                System.out.println("result:" + fs.get());     //打印各个线程（任务）执行的结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                //启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用。
                executorService.shutdown();
            }
        }
    }
}
