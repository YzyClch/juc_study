package com.yzy.test;

public class SingleFactory {

    static public class SingleInstance{
        public void show(){
            System.out.println("方法调用成功");
        }
    }

    private static SingleInstance instance=null;

    public static SingleInstance getInstance(){
        if (instance==null){
            synchronized (SingleFactory.class){
                if (instance==null){
                    instance=new SingleInstance();
                }
            }
        }
        return instance;
    }


}
