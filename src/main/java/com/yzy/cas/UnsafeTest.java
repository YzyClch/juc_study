package com.yzy.cas;

import com.yzy.UnsafeUtil;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {

    private String s="?";


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Unsafe unsafe = UnsafeUtil.getUnsafe();
        UnsafeTest unsafeTest = new UnsafeTest();

        Field s = unsafeTest.getClass().getDeclaredField("s");

        System.out.println();

        unsafe.compareAndSwapObject(unsafeTest,unsafe.objectFieldOffset(s),"?","!");
        System.out.println(unsafeTest.s);
    }

}
