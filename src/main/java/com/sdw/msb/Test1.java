package com.sdw.msb;

/**
 * @ClassName Test1
 * @Description TODO
 * @Author: 索德文
 * @date 2022/1/2 16:17
 * @Version 1.0
 */
public class Test1 {
    private static volatile boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            long l = 0L;
            while (flag) {
                l++;
                if (l == 1000_0000_0000L) {
                    flag = false;
                }
            }
            System.out.println("end l = " + l);
        });

        t.start();

        Thread.sleep(1);
//
//        flag = false;
    }
}
