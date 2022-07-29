package com.sdw.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName ListTest
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/14 17:54
 * @Version 1.0
 */

// java.util.ConcurrentModificationException  并发修改异常
public class ListTest {
    public static void main(String[] args) {
        // 并发下 ArrayList 是不安全的
        /**
         * 解决方案：
         * 1.List<String> list = new Vector<>();
         * 2.List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3.List<String> list = new CopyOnWriteArrayList<>();
         */
        // CopyOnWrite 写入时复制  COW  计算机程序设计领域的一种优化策略
        // 多个线程调用的时候，list，读取的收，是固定的，会存在写入（覆盖）
        // 在写入的时候避免覆盖，造成数据问题
        // 读写分离
        // CopyOnWriteArrayList 比 Vector NB 在哪？CopyOnWriteArrayList没使用synchronized锁，synchronized锁会降低效率
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
