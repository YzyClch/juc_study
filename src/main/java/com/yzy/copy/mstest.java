package com.yzy.copy;

import com.yzy.SleepUtil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class mstest {


    public static ReentrantLock lock=new ReentrantLock();
    public static Condition condition= lock.newCondition();

    public static void main(String[] args) {



        new Thread(()->{
            SleepUtil.sleep3();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
//            SleepUtil.sleep3();
            condition.signal();
            System.out.println("t2执行完毕");
        },"t2").start();
    }
}
