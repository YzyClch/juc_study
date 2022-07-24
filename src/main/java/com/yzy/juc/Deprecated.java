package com.yzy.juc;

import java.util.concurrent.TimeUnit;

public class Deprecated {


    private static Runnable r= () -> {
        while (true){
            System.out.println("打印中。。。。");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };


    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(r);
        t.start();

        TimeUnit.SECONDS.sleep(3);
        t.suspend();
        System.out.println("暂停调用。3s");
        TimeUnit.SECONDS.sleep(3);
        System.out.println(t.getState());
        System.out.println("线程恢复3s");
        t.resume();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("准备stop");
        t.stop();

        System.out.println("end");
        while (true);

    }
}
