package com.yzy.copy;

import java.util.concurrent.locks.ReentrantLock;

public class FOr {

    public static void main(String[] args) {

        int i=0;
        for (; i !=3; i++) {
            System.out.println(i);
        }
        System.out.println(i);

//        new ReentrantLock()
    }
}
