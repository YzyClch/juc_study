package com.yzy.threadPool;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
//import java.util.concurrent.*;

public class MyThreadPoolStudy {

    static int successCount=0;

    @SuppressWarnings("all")
    public static ArrayBlockingQueue<Runnable> makeProxyQueue(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ArrayBlockingQueue.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("\n========My BlockingQueue Method intercept==========\n");
            System.out.println("ArrayBlockingQueue method invoke ->"+method.getName());
            System.out.println("ArrayBlockingQueue method args ->\n");
            for (Object object : objects) {
                System.out.println(object.toString());
            }
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("ArrayBlockingQueue method result ->"+result);
//
            System.out.println("=========================end========================\n");
            return result;            });
        return (ArrayBlockingQueue<Runnable>) enhancer.create(new Class[]{int.class}, new Object[]{1});
    }

    public static RejectedExecutionHandler getRejectHandler(){
//        return new ThreadPoolExecutor.DiscardPolicy();
        return new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("【拒绝策略被调用了"+((MyTask)r).taskName+"】");
                // 不丢弃这个任务，而是直接运行任务的run方法 这个策略就是main线程往下走的
                new ThreadPoolExecutor.CallerRunsPolicy().rejectedExecution(r,executor);
                // 线程池默认的拒绝策略：抛出异常
//                new ThreadPoolExecutor.AbortPolicy().rejectedExecution(r,executor);
                // 把阻塞队列头部的任务抛弃(也就是抛弃等待时间最久的任务)，把当前任务加进去
//                new ThreadPoolExecutor.DiscardOldestPolicy().rejectedExecution(r,executor);
                // 方法为空，相当于直接抛弃这个任务
//                new ThreadPoolExecutor.DiscardPolicy().rejectedExecution(r,executor);
            }
        };
    }

    static class MyTask implements Runnable{
        private String taskName;

        public MyTask(String taskName){
            this.taskName=taskName;
        }
        @Override
        public void run() {
            System.out.println("【"+Thread.currentThread().getName()+" start a task:"+taskName+"】");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true){
                throw new IllegalArgumentException("抛出异常测试");
            }

            System.out.println("【"+Thread.currentThread().getName()+" task end:"+taskName+"】");
            successCount++;
        }
    }

    public static Runnable getTask(){
        return new Runnable() {
            @Override
            public void run() {

            }
        };
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {


        ArrayBlockingQueue<Runnable> myBlockingQueue = makeProxyQueue();
        RejectedExecutionHandler rejectHandler = getRejectHandler();
        Runnable task = getTask();
        System.out.println(Runtime.getRuntime().availableProcessors()); //内核数


        /**
         * Creates a new ThreadPoolExecutor with the given initial parameters.
         * 形参:
         * corePoolSize – the number of threads to keep in the pool, even if they are idle, unless allowCoreThreadTimeOut is set 核心线程数
         * maximumPoolSize – the maximum number of threads to allow in the pool 最大线程数
         * keepAliveTime – when the number of threads is greater than the core, this is the maximum time that excess idle threads will wait for new tasks before terminating. 超过核心线程数的最大存活时间
         * unit – the time unit for the keepAliveTime argument 存活时间单位
         * workQueue – the queue to use for holding tasks before they are executed. This queue will hold only the Runnable tasks submitted by the execute method. 工作队列？
         * threadFactory – the factory to use when the executor creates a new thread 线程工厂
         * handler – the handler to use when execution is blocked because the thread bounds and queue capacities are reached 拒绝策略
         * 抛出:
         * IllegalArgumentException – if one of the following holds: corePoolSize < 0 keepAliveTime < 0 maximumPoolSize <= 0 maximumPoolSize < corePoolSize
         * NullPointerException – if workQueue or threadFactory or handler is null
         */
        ThreadPoolExecutor t=new ThreadPoolExecutor(3, 5, 5,
                TimeUnit.SECONDS, myBlockingQueue, Executors.defaultThreadFactory(),rejectHandler);

        for (int i = 0; i < 100; i++) {
            t.execute(new MyTask("Task:"+i));
//            Future<?> submit = t.submit(new MyTask("Task:" + i));//异常被FutureTask的run方法try catch了，所以不会抛出
//            submit.get()
        }
        t.shutdown();
        t.shutdownNow();
        t.terminated();
        t.tryTerminate();
//        while (true){
//            System.out.println("当前执行完成数："+successCount);
//        }
        LockSupport.park();
    }



}
