package com.yzy.cas;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class FieldTest {


    @Data
    @AllArgsConstructor
    static class User{
        public volatile String name;
         volatile   int id;
    }

    @Test
    public void test(){
        User user = new User("yzy",1);
        AtomicReferenceFieldUpdater<User, String> npd = AtomicReferenceFieldUpdater.newUpdater(User.class, String.class, "name");
        npd.compareAndSet(user,"yzy","yyz");
        System.out.println(user.getName());
    }

    public static void main(String[] args) {

        User user = new User("yzy",1);


        AtomicIntegerFieldUpdater<User> idUpd = AtomicIntegerFieldUpdater.newUpdater(User.class, "id");

        idUpd.compareAndSet(user,user.getId(),5);

        System.out.println(user.id);
    }
}
