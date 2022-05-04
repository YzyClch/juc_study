package com.yzy.list;

import java.util.concurrent.ConcurrentHashMap;

public class MapTest {


    public static void main(String[] args) {
        ConcurrentHashMap map=new ConcurrentHashMap();
        map.put("","");
        Object o = map.get("");
    }
}
