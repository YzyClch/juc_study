package com.yzy.juc;

import java.util.concurrent.Exchanger;

public class ExchangerTest {


    /**
     * 两个线程可以通过 Exchanger 交换变量
     * 如果有一个线程没有执行exchange，另一个就会阻塞等待
     */
    public static Exchanger<String> e = new Exchanger<>();
    public static void main(String[] args) throws InterruptedException {

        String xx = e.exchange("xx");
        System.out.println(xx);
//        new Thread(()->{
//            try {
//                String test = e.exchange("test");
//                System.out.println(test);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//
//        }).start();
//
//        new Thread(()->{
//            try {
//                String test2 = e.exchange("test2");
//                System.out.println(test2);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//
//        }).start();

    }
}
