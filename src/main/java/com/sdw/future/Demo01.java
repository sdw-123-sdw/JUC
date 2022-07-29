package com.sdw.future;

/**
 * @ClassName Demo01
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/17 9:20
 * @Version 1.0
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用：最先接触的就是Ajax，Java中使用 CompletableFuture
 * 异步执行
 * 成功回调
 * 失败回调
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        // 没有返回值的  runAsync  异步回调
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + "异步调用,runAsync -> Void");
//        });
//
//        System.out.println("111111111112222222222");
//
//        completableFuture.get();   // 获取阻塞执行结果

        // 有返回值的  supplyAsync  异步回调
        // 成功和失败的回调
        // 返回的是错误信息
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "异步调用,supplyAsync -> String");
            int i = 10/0;
            return "12341234kjhsarfui";
        });

        System.out.println(completableFuture.whenComplete((t, v) -> {
            System.out.println("t = " + t);    // 正常的返回结果
            System.out.println("v = " + v);    // 错误信息：java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally((e) -> { // 回调失败后的方法 exceptionally
            System.out.println(e.getMessage());
            return "500";   // 可以获取到错误的返回结果
        }).get());
    }
}
