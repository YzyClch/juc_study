package com.yzy;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeUtil {

    private static volatile Unsafe UNSAFE;

    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        if (UNSAFE==null){
            synchronized (UnsafeUtil.class){
                if (UNSAFE==null){
                    Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                    theUnsafe.setAccessible(true);
                    UNSAFE = (Unsafe)theUnsafe.get(null);
                }
            }
        }
        return UNSAFE;
    }

}
