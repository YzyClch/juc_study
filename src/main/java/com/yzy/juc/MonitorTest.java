package com.yzy.juc;

public class MonitorTest {


    private static Object lock =new Object();

    public static void main(String[] args) {

        synchronized (lock){
            synchronized (lock){
                System.out.println("good!");
            }
        }

    }
}
