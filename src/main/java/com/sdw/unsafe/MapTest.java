package com.sdw.unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName MapTest
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/14 20:09
 * @Version 1.0
 */
// ConcurrentModificationException
public class MapTest {
    public static void main(String[] args) {
        // map 是这样用的么？ 不是，工作中不用 HashMap
        // 默认等价于什么  new HashMap<>(16, 0.75); // 加载因子、初始化容量
        // Map<String, String> map = new HashMap<>();
        // ConcurrentHashMap不能接受null的key和null的value,会抛出空指针异常
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
