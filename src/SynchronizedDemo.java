package src;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/9 11:16
 */
public class SynchronizedDemo {
    static final Object room = new Object();
    static int count = 0;
    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 100; i++){
                synchronized (room){
                    count++;
                    System.out.println(count);
                }
            }
        },"t1");

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 100; i++){
                synchronized (room){
                    count--;
                    System.out.println(count);
                }
            }
        },"t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("------------" + count);
    }
}
