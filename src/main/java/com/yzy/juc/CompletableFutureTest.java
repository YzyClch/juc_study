package com.yzy.juc;

import com.yzy.SleepUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {

        List<Integer>res=new ArrayList<>();


        Object o = CompletableFuture.supplyAsync(s1).whenComplete((r, e) -> System.out.println(Thread.currentThread().getName())).thenApply(

                new Function<Integer, Object>() {
                    @Override
                    public Object apply(Integer integer) {
                        System.out.println(Thread.currentThread().getName());
                        System.out.println("apply return null");
                        return null;
                    }
                }
        ).thenApplyAsync(new Function<Object, Object>() {
            @Override
            public Object apply(Object o) {
                System.out.println(Thread.currentThread().getName());
                System.out.println(o);
                System.out.println("apply2 return null");
                return null;
            }
        }).get();
        System.out.println(o);
        SleepUtil.sleep60();

    }

    public static Supplier<Integer> s1 = () -> {
        try {
            System.out.println("当前线程 "+Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 100;
    };

    public static Supplier<Integer> s2 = () -> {
        try {
            System.out.println("当前线程 "+Thread.currentThread().getName());

            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 255;
    };

    public static Supplier<Integer> s3 = () -> {
        try {
            System.out.println("当前线程 "+Thread.currentThread().getName());

            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 33;
    };

    public static Supplier<Integer> s4 = () -> {
        try {
            System.out.println("当前线程 "+Thread.currentThread().getName());

            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 99;
    };



    @Test
    public void comparePriceTest(){

        long startx = System.currentTimeMillis();
        List<Supplier<Integer>> suppliers = Arrays.asList(s1, s2, s3, s4);
        Stream<Supplier<Integer>> stream = suppliers.stream();
        Stream<CompletableFuture<Integer>> completableFutureStream = stream.map(s -> CompletableFuture.supplyAsync(s));
        List<CompletableFuture<Integer>> collect = completableFutureStream.collect(Collectors.toList());
        Stream<CompletableFuture<Integer>> stream1 = collect.stream();
        Stream<Integer> integerStream = stream1.map(c -> c.join());
        List<Integer> resultList = integerStream.collect(Collectors.toList());
        System.out.println(resultList);
        long endx= System.currentTimeMillis();
        System.out.println("用时："+(endx-startx)+"毫秒");



        long start2 = System.currentTimeMillis();
        s1.get();
        s2.get();
        s3.get();
        s4.get();
        long end2 = System.currentTimeMillis();
        System.out.println("用时："+(end2-start2)+"毫秒");



        long start1 = System.currentTimeMillis();

        CompletableFuture<Integer> c1 = CompletableFuture.<Integer>supplyAsync(s1);
        CompletableFuture<Integer> c2 = CompletableFuture.<Integer>supplyAsync(s2);
        CompletableFuture<Integer> c3 = CompletableFuture.<Integer>supplyAsync(s3);
        CompletableFuture<Integer> c4 = CompletableFuture.<Integer>supplyAsync(s4);
        System.out.println(c1.join());
        System.out.println(c2.join());
        System.out.println(c3.join());
        System.out.println(c4.join());

        long end1 = System.currentTimeMillis();
        System.out.println("用时："+(end1-start1)+"毫秒");





        long start = System.currentTimeMillis();
        List<Integer> resultListx = Arrays.asList(s1, s2, s3, s4).stream()
                .map(s -> CompletableFuture.supplyAsync(s)).collect(Collectors.toList()).stream()
                .map(c -> c.join()).collect(Collectors.toList());
        System.out.println(resultListx);
        long end= System.currentTimeMillis();
        System.out.println("用时："+(end-start)+"毫秒");

        long start4 = System.currentTimeMillis();
        List<Integer> resultList2 = Arrays.asList(s1, s2, s3, s4).stream()
                .map(s -> CompletableFuture.supplyAsync(s))
                .map(c -> c.join()).collect(Collectors.toList());
        System.out.println(resultList);
        long end4= System.currentTimeMillis();
        System.out.println("用时："+(end4-start4)+"毫秒");


        long start5 = System.currentTimeMillis();
        List<Integer> resultList3 = Arrays.asList(s1, s2, s3, s4).parallelStream()
                .map(s -> CompletableFuture.supplyAsync(s))
                .map(c -> c.join()).collect(Collectors.toList());
        System.out.println(resultList);
        long end5= System.currentTimeMillis();
        System.out.println("用时："+(end5-start5)+"毫秒");
    }



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
        // 运行结果，可能是result 也可能是异常
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
