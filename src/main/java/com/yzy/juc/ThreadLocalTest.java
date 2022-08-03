package com.yzy.juc;

//import com.yzy.copy.ThreadLocal;

import org.junit.Test;

public class ThreadLocalTest {


    public static void main(String[] args) {
        ThreadLocal<Object> t = new ThreadLocal<>();

        t.set(new Object());

        t.get();

        t.remove();
    }

    @Test
    public void test(){
        for (int i = 0; i < 20; i++) {
            new ThreadLocal<Object>().set(new Object());
        }
        new ThreadLocal<Object>().set(new Object());
    }
}
