package com.yzy.test;

import org.junit.Test;

public class SafeSingleTest {







    @Test
    public void test(){
        for (int i = 0; i < 3; i++) {
            SingleFactory.getInstance().show();
        }
    }


}
