package com.yzy.juc;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class CompletableFutureTest {


    @Test
    public void test1() throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.<String>supplyAsync(()->{
            System.out.println("开始执行。task1");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(10/0);
            return "success!";
        });
        System.out.println(" . . . . ");
        future.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String result, Throwable throwable) {
                System.out.println("运行结束：结果 "+result);
            }
        });
        future.exceptionally(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable throwable) {
                System.out.println("程序出现了异常！");
                return null;
            }
        });




        TimeUnit.SECONDS.sleep(1000);

    }
}
