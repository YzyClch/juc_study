package com.yzy.juc;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {


    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        semaphore.release();
        semaphore.acquire();
    }
}
