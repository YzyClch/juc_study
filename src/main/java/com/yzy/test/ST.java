package com.yzy.test;

import com.yzy.SleepUtil;
import org.junit.Test;

import java.util.concurrent.locks.StampedLock;

public class ST {

    static class Source{
        private Integer number;

        public Source(int number) {
            this.number=number;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }
    }


    public static StampedLock stampedLock = new StampedLock();
    public static Source source=new Source(1);

    @Test
    public void test() throws InterruptedException {

        new Thread(()->{
            long stamped = stampedLock.writeLock();
            Integer number = source.getNumber();
            SleepUtil.sleep3();
            number+=10;
            source.setNumber(number);
            stampedLock.unlockWrite(stamped);

        }).start();

        SleepUtil.sleep1();

        long s = stampedLock.tryOptimisticRead();
        Integer number = source.getNumber();
        System.out.println("读取到了值 "+number);
        System.out.println("验证："+stampedLock.validate(s));
        if (!stampedLock.validate(s)){
            long l = stampedLock.readLock();
            System.out.println("悲观读");
            System.out.println(source.getNumber());
            stampedLock.unlockRead(l);
        }

        SleepUtil.sleep60();
    }

    @Test
    public void test2(){
        new Thread(()->{
            long stamped = stampedLock.tryOptimisticRead();
            Integer number = source.getNumber();
            System.out.println("乐观读：number "+number);
            SleepUtil.sleep3();
            boolean validate = stampedLock.validate(stamped);
            if (!validate){
                System.out.println("升级为悲观读");
                long rs = stampedLock.readLock(); //写锁释放才能获取到读锁
                number= source.getNumber();
                System.out.println("读到结果："+number);
                SleepUtil.sleep3();
                stampedLock.unlockRead(rs);
            }


        }).start();

        SleepUtil.sleep1();

        long s = stampedLock.writeLock();
        Integer number = source.getNumber();
       number+=100;
       source.setNumber(number);
//       stampedLock.unlockWrite(s);
        SleepUtil.sleep60();
    }
}
