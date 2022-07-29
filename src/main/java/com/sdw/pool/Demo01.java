package com.sdw.pool;

import java.util.concurrent.*;

/**
 * @ClassName Demo01
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/16 14:54
 * @Version 1.0
 *
 * Executors 工具类、五大方法
 * 使用了线程池之后，使用线程池来创建线程
 */
public class Demo01 {
    public static void main(String[] args) {
        /**
         * AbortPolicy：银行满了，还有人进来，不处理这个人的，抛出异常
         * CallerRunsPolicy：哪来的去哪里，哪个线程来的回哪个线程，回去让那个线程处理
         * DiscardPolicy：队列满了不抛出异常，丢弃任务
         * DiscardOldestPolicy：队列满了，尝试去和目前正在执行种的最早执行的线程竞争，也不会抛出异常；试探最早的是否结束，如果结束就跟进，没结束就不处理，不抛出异常。不是插队
         */
        int i1 = Runtime.getRuntime().availableProcessors();   // 获取CPU的核数
        // 自定义线程池
        ExecutorService service = new ThreadPoolExecutor(
                2,   // 核心线程数，一直开着的
                i1,    // 最大线程数，所能容纳的最大的线程数
                3,     // 3秒后没有使用则将非核心线程释放
                TimeUnit.SECONDS,     // 超时单位
                new LinkedBlockingQueue<>(3),    // 阻塞队列，3是队列的最大容纳量
                Executors.defaultThreadFactory(),     // 默认线程工厂策略
                new ThreadPoolExecutor.DiscardOldestPolicy());    // 参照上面的前三个创建的拒绝策略，这个也是默认拒绝策略

        // 线程池用完，程序结束，关闭线程池
        try {
            // 最大承载：队列最大容纳量 + 最大线程容纳量
            // 超出最大承载抛 RejectedExecutionException
            for (int i = 0; i < 100; i++) {
                final int temp = i;
                // 使用了线程池之后，使用线程池来创建线程
                service.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "----->" + temp);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }
}
