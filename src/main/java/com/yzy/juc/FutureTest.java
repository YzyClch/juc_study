package com.yzy.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FutureTest {
    public static void main(String[] args) throws InterruptedException {
        Supplier method = new Supplier() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Thread.currentThread().getName()+":运行结束";
            }
        };

        CompletableFuture completableFuture = CompletableFuture.supplyAsync(method);



        CompletableFuture.supplyAsync(method).whenComplete(new BiConsumer() {
            @Override
            public void accept(Object o, Object o2) {
                System.out.println(o);
                System.out.println(o2);
            }
        }).exceptionally(new Function() {
            @Override
            public Object apply(Object o) {
                return o;
            }
        });


        TimeUnit.MINUTES.sleep(1);
    }




}
