package com.sdw.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName SetTest
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/14 18:20
 * @Version 1.0
 */
public class SetTest {
    public static void main(String[] args) {
        // 可得问题：ConcurrentModificationException
        /**
         * 解决方案：
         * 1.Set<String> set = Collections.synchronizedSet(new HashSet<>());
         * 2.Set<String> set = new CopyOnWriteArraySet<>();
         */
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
