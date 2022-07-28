package com.yzy.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class CASThreadLock {

    public static class CasLock{

        public static AtomicReference ar = new AtomicReference<Thread>();

        public void lock() {
            for (;;){
                if (ar.compareAndSet(null,Thread.currentThread()))break;
            }
        }


        public void unlock() {
            for (;;){
                if (ar.compareAndSet(Thread.currentThread(),null))break;
            }
        }


    }







    public static void main(String[] args) throws InterruptedException {
        CasLock lock = new CasLock();


        new Thread(()->{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"得到了锁");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"释放锁");
            lock.unlock();
        }).start();


        new Thread(()->{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"得到了锁");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"释放锁");
            lock.unlock();
        }).start();


        TimeUnit.SECONDS.sleep(100000);


    }








}
