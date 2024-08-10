package src;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/10 10:48
 */
public class SpinLock {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "------come in");
        while (!atomicReference.compareAndSet(null,thread)){
            Thread.sleep(1000);
            System.out.println(thread.getName() + "------正在自旋");
        }
        System.out.println(thread.getName() + "------自旋成功");
    }

    public void unLock(){
        Thread thread = Thread.currentThread();

        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName() + "------解锁成功");
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLock lock = new SpinLock();

        new Thread(() -> {
            try {
                lock.lock();
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unLock();
        },"t1").start();

        Thread.sleep(1000);

        new Thread(() -> {
            try{
                lock.lock();
            }catch (Exception e){
                e.printStackTrace();
            }
            lock.unLock();
        },"t2").start();
    }
}
