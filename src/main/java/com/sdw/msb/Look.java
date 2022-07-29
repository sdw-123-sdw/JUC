package com.sdw.msb;

/**
 * @ClassName Look
 * @Description TODO
 * @Author: 索德文
 * @date 2022/1/2 19:19
 * @Version 1.0
 */
public class Look {
    private static boolean flag = true;

    private static void m() {
        System.out.println("start");
        while (flag) {}
        System.out.println("end");
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(Look::m, "thread01").start();
        Thread.sleep(1);
        flag = false;
    }
}
