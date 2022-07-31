package com.yzy.threadPool;

import com.yzy.SleepUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {



//        new ArrayBlockingQueue<Integer>(10).poll(10,)

        System.out.println(TimeUnit.MINUTES.toNanos(100));
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(10);
//        poolExecutor.scheduleWithFixedDelay();
//        poolExecutor.scheduleAtFixedRate()

        SleepUtil.sleep();
    }






}
