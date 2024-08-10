package src;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/10 12:03
 */
public class TestLiveLock {
    private static volatile int count = 10;
    static final Object lock = new Object();
    public static void main(String[] args){
        new Thread(() -> {
            while (count > 0){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count--;
                System.out.println("线程-count" + count);
            }
        },"t1").start();

        new Thread(() -> {
            while(count < 20){
                try{
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count++;
                System.out.println("线程count------:" + count);
            }
        },"t2").start();
    }
}
