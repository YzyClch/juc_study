package com.yzy.juc;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

public class LockSportTest {


    public static void main(String[] args) {
        while (true){


            // 返回当前线程中断标识、清空中断标识
            boolean interrupted = Thread.interrupted();
            // 中断标识设为true
            Thread.currentThread().interrupt();
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
}
