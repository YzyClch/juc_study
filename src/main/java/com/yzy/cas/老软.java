package com.yzy.cas;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class 老软 {


    public static void main(String[] args) {

        HashMap<String, String> map1 = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("key","value");
        HashMap<String, String> map3 = new HashMap<>();
        List<HashMap<String, String>> list = Arrays.asList(map1, map2, map3);
        Optional<HashMap<String, String>> optional = list.stream().filter(i -> i.containsKey("key")).findFirst();
        optional.ifPresent(stringStringHashMap -> System.out.println(stringStringHashMap.get("key")));


    }
}
