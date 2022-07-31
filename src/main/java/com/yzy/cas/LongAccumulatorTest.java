package com.yzy.cas;

import org.junit.Test;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

public class LongAccumulatorTest {






@Test
    public void test1(){


    LongAccumulator la = new LongAccumulator(new LongBinaryOperator() {
        /**
         *
         * @param left 当前值
         * @param right 传入的值
         * @return
         */
        @Override
        public long applyAsLong(long left, long right) {
            System.out.println("执行了自定义applyAsLong方法");
            return left+right;
        }
    }, 10);

    la.accumulate(100);

    System.out.println(la.intValue());


}





}
