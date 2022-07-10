package com.yzy.threadPool;

public class ST {




    static class Father{
        public synchronized void doSomeThing(){
            System.out.println("father do something");
        }
    }


    static class Son extends Father{
        @Override
        public synchronized void doSomeThing() {
            System.out.println("son do something");
            super.doSomeThing();
            System.out.println("x");
            this.doSomeThing();
        }
    }

    public static void main(String[] args) {
        new Son().doSomeThing();
    }

}
