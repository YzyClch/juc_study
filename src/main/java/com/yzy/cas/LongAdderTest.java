package com.yzy.cas;

import org.junit.Test;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {



    @Test
    public void test1(){
        LongAdder longAdder = new LongAdder();
        System.out.println(longAdder.longValue());
        longAdder.add(-1);
        System.out.println(longAdder.longValue());
    }
}
