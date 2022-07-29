package com.sdw.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SemaphoreDemo
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/15 18:18
 * @Version 1.0
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 默认线程数量：模拟停车位,可以限流
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                // acquire()  得到一个
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(2);  // 线程停止
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // release()  释放一个
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
