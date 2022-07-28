package com.yzy.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest {

    static class MyTask extends RecursiveTask<Integer> {


        private final Integer i;

        public MyTask(Integer i) {
            this.i=i;
        }

        @Override
        protected Integer compute() {
            if (i!=1){
                System.out.println(Thread.currentThread().getName() +"拆分任务 i="+i);
                MyTask task1 = new MyTask(i / 2);
                task1.fork();
                MyTask task2 = new MyTask(i / 2);
                task2.fork();
                System.out.println(Thread.currentThread().getName() +"聚合拆分结果："+(task1.join()+task2.join()));

                return task1.join()+task2.join();
            }
            System.out.println(Thread.currentThread().getName() +"返回结果 i="+i);
            return i;
        }
    }





    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);

        System.out.println(submit.get());
    }
}
