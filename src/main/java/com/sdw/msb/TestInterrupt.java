package com.sdw.msb;

/**
 * @ClassName TestInterrupt
 * @Description TODO
 * @Author: 索德文
 * @date 2022/1/2 16:36
 * @Version 1.0
 */
public class TestInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
             // action
            }
            System.out.println("end");
        });
        thread.start();

        Thread.sleep(1);

        thread.interrupt();
    }
}
