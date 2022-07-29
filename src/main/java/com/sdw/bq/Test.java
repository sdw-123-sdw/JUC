package com.sdw.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/15 20:36
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        // test1();
        // test2();
//        try {
//            test3();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            test4();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 抛出异常
     */
    public static void test1() {
        // 此处泛型位置报错需要写队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.element());   //查看队首元素是谁

        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));

        // java.lang.IllegalStateException: Queue full  抛出异常
        // System.out.println(arrayBlockingQueue.add("d"));

        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());

        // java.util.NoSuchElementException  抛出异常
        // System.out.println(arrayBlockingQueue.remove());
    }

    /**
     * 不抛出异常，有返回值
     */
    public static void test2() {
        // 此处泛型位置报错需要写队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        // 存用offer
        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.element());   //查看队首元素是谁
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));

        // 返回false
        // System.out.println(arrayBlockingQueue.offer("d"));

        // 取用poll
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.peek());   // 查看队首元素
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());

        // 返回null
        System.out.println(arrayBlockingQueue.poll());

    }

    /**
     * 等待（一直阻塞）
     */
    public static void test3() throws InterruptedException {
        // 此处泛型位置报错需要写队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        // 一直阻塞
        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.put("c");
        // arrayBlockingQueue.put("d");   // 队列没有位置了，一直阻塞，程序不会停止

        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());   // 队列为空，会一直阻塞，程序不会停止

    }

    /**
     * 等待，阻塞（超时退出）
     */

    public static void test4() throws InterruptedException {
        // 此处泛型位置报错需要写队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.offer("b", 2, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.offer("c", 2, TimeUnit.SECONDS));

        System.out.println(arrayBlockingQueue.offer("d", 2, TimeUnit.SECONDS));   // 队列已满，等待2秒后，返回false，并结束程序

        System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));   // 队列为空，等待2秒后返回null，程序结束

    }
}
