package src;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/11 20:20
 */
public class AlternateDemo {
    public static void main(String[] args) throws InterruptedException{
        AwaitSignal demo = new AwaitSignal(5);
        Condition a = demo.newCondition();
        Condition b = demo.newCondition();
        Condition c = demo.newCondition();

        new Thread(() -> {
            demo.print("a",a,b);
        }).start();
        new Thread(() -> {
            demo.print("b",b,c);
        }).start();
        new Thread(() -> {
            demo.print("c",c,a);
        }).start();

        Thread.sleep(1000);
        demo.lock();
        try {
            a.signal();
        } finally {
            demo.unlock();
        }

    }
}

class AwaitSignal extends ReentrantLock {
    private int loopNumber;

    public AwaitSignal(int loopNumber) {
        this.loopNumber = loopNumber;
    }
    //参数1：打印内容  参数二：条件变量  参数二：唤醒下一个
    public void print(String str, Condition condition,Condition next) {
        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                condition.await();
                System.out.println(str);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }
        }
    }
}