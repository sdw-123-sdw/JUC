package com.sdw.add;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchDemo
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/14 21:42
 * @Version 1.0
 */
// 计数器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 总数是6，倒计时, 必须要执行任务的时候， 再使用！
        CountDownLatch countDownLatch = new CountDownLatch(2);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();  // -1
            }, String.valueOf(i)).start();
        }

        // 等待计数器归零， 然后再向下执行
        countDownLatch.await();
        System.out.println("close door");
        countDownLatch.countDown();  // -1
    }
}
