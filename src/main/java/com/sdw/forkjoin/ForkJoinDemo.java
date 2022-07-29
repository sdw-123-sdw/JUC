package com.sdw.forkjoin;

/**
 * @ClassName ForkJoinDemo
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/16 21:23
 * @Version 1.0
 */

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算任务
 * 如何使用forkJoin
 * 1. ForkJoinPool 通过他来执行
 * 2. 计算任务 ForkJoinPool.execute(ForkJoinTask task)
 * 3. 计算类要继承ForkJoinTask
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private Long start;
    private Long end;

    // 临界值
    private Long temp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    public void test() {

    }

    // 计算方法
    @Override
    protected Long compute() {
        if((end - start) < temp) {
            Long sum = 0L;
            for (Long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // 分支合并计算
            long middle = start + (end - start) / 2;
//            long l = end + start >>> 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork();   // 拆分任务，把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork();   // 拆分任务，把任务压入线程队列
            return task1.join() + task2.join();
        }
    }
}
