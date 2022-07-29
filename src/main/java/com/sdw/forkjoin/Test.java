package com.sdw.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @ClassName Test
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/16 22:13
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();  // 7858
//        test2();  // 4576
        test3();   // 200
    }

    // 普通程序员
    public static void test1() {
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long i = 1L; i < 10_0000_0000; i++) {
            sum += i;
        }

        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + "时间" + (end - start));
    }

    // 会使用ForkJoin
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinDemo = new ForkJoinDemo(1L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinDemo);// 提交任务，异步
        Long aLong = submit.get();

        long end = System.currentTimeMillis();
        System.out.println("sum = " + aLong + "时间" + (end - start));
    }

    public static void test3() {
        long start = System.currentTimeMillis();
        // stream并行流
        long sum = LongStream.rangeClosed(1L, 10_0000_0000L).parallel().reduce(0, Long::sum);// parallel并行
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + "时间" + (end - start));
    }
}
