package com.sdw.bq;

import java.util.concurrent.SynchronousQueue;

/**
 * @ClassName SynchronousQueueDemo
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/16 11:03
 * @Version 1.0
 * 和其他的BlockingQueue不一样，SynchronousQueue  不存储元素
 * put了一个元素，必须从里面先take取出来，否则不能再put进去值
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();  // 同步队列

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    synchronousQueue.put(Thread.currentThread().getName() + "===>" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T2").start();
    }
}
