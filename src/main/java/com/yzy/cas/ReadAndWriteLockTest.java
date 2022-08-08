package com.yzy.cas;

import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadAndWriteLockTest {


    public static Boolean cached=false;
    public static Object data = null;
    public static ReentrantReadWriteLock lock=new ReentrantReadWriteLock();



    @Test
    public void test(){

        for (int i = 0; i < 100; i++) {
            new Thread(()->{if (cached){
                lock.readLock().lock();
                System.out.println("获取到了缓存"+data);
                lock.readLock().unlock();
            }else {
                lock.writeLock().lock();
                if (!cached){ //双端校验
                    cached=true;
                    data=new Object();
                    lock.readLock().lock();
                    lock.writeLock().unlock();
                    System.out.println("缓存中没有值，设置并使用了缓存"+data); //只有一个线程能进到这步
                    lock.readLock().unlock();
                }else {
                    lock.writeLock().unlock();
                }
                System.out.println("写锁情况下已被其他线程写入"+data);


            }}).start();
        }








    }
}
