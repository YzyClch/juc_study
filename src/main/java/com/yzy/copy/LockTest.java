package com.yzy.copy;

import com.yzy.SleepUtil;

public class LockTest {


    /**
     * -XX:+DoEscapeAnalysis -XX:+EliminateLocks
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {


        Object lock = new Object();

        int i=0;
        while (i!=2){
            new Thread(()->{
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName());
                }
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName());
                }
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName());
                }
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName());
                }
            }).start();
            i++;

        }


        synchronized (lock){
            System.out.println(Thread.currentThread().getName());
        }
        synchronized (lock){
            System.out.println(Thread.currentThread().getName());
        }
        synchronized (lock){
            System.out.println(Thread.currentThread().getName());
        }
        synchronized (lock){
            System.out.println(Thread.currentThread().getName());
        }
        SleepUtil.sleep60();
    }

}
