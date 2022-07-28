package com.yzy.juc;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSportTest {


    public static void main(String[] args) {
        while (true){



            // 中断标识设为true
            Thread.currentThread().interrupt();
            // 返回当前线程中断标识、清空中断标识
            boolean interrupted = Thread.interrupted();
            System.out.println(interrupted);
            interrupted = Thread.interrupted();
            System.out.println(interrupted);
            // 返回中断标识
            boolean interrupted1 = Thread.currentThread().isInterrupted();
        }
    }


    @Test
    public void test(){
        Thread.currentThread().interrupt();
        Thread.interrupted();
        System.out.println(Thread.currentThread().isInterrupted());
    }




    @Test
    public void waitTest() throws InterruptedException {

        Object lock = new Object();

        new Thread(()->{
            synchronized (lock){
                System.out.println("dododoodod");
                try {
                    lock.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("继续");
            }
        }).start();

        new Thread(()->{
            synchronized (lock){
                System.out.println("进入了t2");
                try {
                    Thread.sleep(50000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("结束");
            }}
        ).start();
        TimeUnit.SECONDS.sleep(10000L);
    }

    @Test
    public void lockSportTest(){

//        LockSupport.park();
//
//        synchronized(){
//            new Object().wait(100);
//        }
    }
}
