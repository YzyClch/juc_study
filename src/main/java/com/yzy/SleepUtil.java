package com.yzy;

import java.util.concurrent.TimeUnit;

public final class SleepUtil {



    public static void sleep1() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }

    public static void sleep60() throws InterruptedException {
        TimeUnit.MINUTES.sleep(1);
    }

}
