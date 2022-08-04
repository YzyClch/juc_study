package com.yzy.copy;

import org.openjdk.jol.vm.VM;

public class JolTest {

    public static void main(String[] args) {
        System.out.println(VM.current().details());
    }
}
