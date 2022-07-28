package com.yzy.juc;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AbstractQueuedSynchronizerTest {




    public static  class MyQueuedSynchronizer extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            return super.tryAcquire(arg);
        }

        @Override
        protected boolean tryRelease(int arg) {
            return super.tryRelease(arg);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }

        @Override
        protected boolean isHeldExclusively() {
            return super.isHeldExclusively();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyQueuedSynchronizer myQueuedSynchronizer = new MyQueuedSynchronizer();
        myQueuedSynchronizer
        .tryAcquire(1);

        new Thread(()->myQueuedSynchronizer.acquire(1000));
        Thread.sleep(50000L);


    }



}
