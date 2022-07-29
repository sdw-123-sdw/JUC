package com.sdw.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName CallableTest
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/14 21:06
 * @Version 1.0
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // new Thread(new Runnable()).start();
        // 不就是FutureTask实现了runnable接口的子接口RunnableFuture
        // new Thread(new FutureTask<V>(Callable)).start();

        MyThread myThread = new MyThread();
        // 适配器
        FutureTask futureTask = new FutureTask(myThread);
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();  // 结果会被缓存， 效率高

        Integer o = (Integer) futureTask.get();   // 获取 Callable 的返回值，但是这个get方法可能会产生阻塞！把它放在最后，或者使用异步通信
        System.out.println(o);
    }
}

// Callable的泛型就相当于call方法的返回值
class MyThread implements Callable<Integer> {
    @Override
    public Integer call() {
        System.out.println("Callable");
        return 10001;
    }
}
