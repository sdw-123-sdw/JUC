package com.sdw.demo01;

/**
 * @ClassName SaleTicketDemo01
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/12 16:07
 * @Version 1.0
 */

/**
 * 真正的多线程开发，公司中的开发，降低耦合性
 * 线程就是一个单独的资源类，没有任何附属的操作
 * 1. 属性，方法
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        // 并发：多个线程操作同一个资源类,把资源类丢入线程
        Ticket ticket = new Ticket();

        // @FunctionalInterface 函数式接口
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

// 资源类   OOP编程思维：面向对象
class Ticket {
    // 属性、方法
    private int number = 50;

    // 买票的方法
    // synchronized 本质：队列，锁
    public synchronized void sale() {
        if(number > 0) {
            System.out.println(Thread.currentThread().getName() + "买了第" + (50 - number-- + 1) + "张票,剩余：" + number);
        }
    }
}
