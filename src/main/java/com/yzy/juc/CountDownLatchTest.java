package com.yzy.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest

{


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl=new CountDownLatch(1);


        cdl.countDown();
//        cdl.

//        cdl.await();
    }
}
