package com.yzy.juc;

import com.yzy.SleepUtil;

public class S {

public static Object l=new Object();
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (l){
                System.out.println(Thread.currentThread().getName()+"得到锁");
                SleepUtil.sleep3();
                System.out.println(Thread.currentThread().getName()+"释放锁");
            }
        },"t1").start();
        new Thread(()->{
            synchronized (l){
                System.out.println(Thread.currentThread().getName()+"得到锁");
                SleepUtil.sleep3();
                System.out.println(Thread.currentThread().getName()+"释放锁");
            }
        },"t2").start();
        new Thread(()->{
            synchronized (l){
                System.out.println(Thread.currentThread().getName()+"得到锁");
                SleepUtil.sleep3();
                System.out.println(Thread.currentThread().getName()+"释放锁");
            }
        },"t3").start();
        while (true);
    }
}
