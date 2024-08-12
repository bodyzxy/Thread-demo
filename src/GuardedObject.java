package src;

import java.util.Arrays;
import java.util.List;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/11 11:46
 */
public class GuardedObject {
    private Object response;
    private final Object lock = new Object();

    //产生结果
    public void complate(Object response){
        synchronized (lock){
            this.response = response;
            System.out.println("notify.....");
            lock.notify();
        }
    }

    //获取结果
    public Object get(long mills){
        synchronized (lock){
            //记录初始时间
            long start = System.currentTimeMillis();
            //已经经历的时间
            long timePassed = 0;
            while (response == null){
                // 假设 millis 是 1000，结果在 400 时唤醒了，那么还有 600 要等
                long waitTime = mills - timePassed;
                System.out.println("waiting for " + waitTime + " milliseconds");

                if (waitTime <= 0){
                    System.out.println("break....");
                    break;
                }

                try{
                    lock.wait(waitTime);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                //如果提前被唤醒，这时已经经历的时间假设为 400
                timePassed = System.currentTimeMillis() - start;
                System.out.println(String.format("timePassed: %d",
                        timePassed));
            }
        }
        return response;
    }

    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            guardedObject.complate(Arrays.asList("a","b","c"));
        },"t1").start();

        Object response = guardedObject.get(2500);
        if (response != null){
            System.out.println(String.format("get response:  ", response.toString()));
        } else {
            System.out.println("can't get response");
        }
    }
}
