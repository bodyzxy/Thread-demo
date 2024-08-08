package src;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/7 22:00
 */
public class LockSupportDemo {
    public static void main(String[] args){
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "---------come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "---------被唤醒");
        },"t1");
        t1.start();

        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
           LockSupport.unpark(t1);
           System.out.println(Thread.currentThread().getName() + "--------发出通知");
        },"t2").start();
    }
}
