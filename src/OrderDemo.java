package src;

import java.util.concurrent.locks.LockSupport;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/11 20:16
 */
public class OrderDemo {
    public static void main(String[] args){
        Thread t1 = new Thread(() -> {
            // 当没有许可时，当前线程暂停运行；有许可时，用掉这个许可，当前线程恢复运行
            LockSupport.park();
            System.out.println("1");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("2");
            // 给线程 t1 发放『许可』（多次连续调用 unpark 只会发放一个『许可』）
            LockSupport.unpark(t1);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
    }
}
