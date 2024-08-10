package src;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/10 12:59
 */
public class NotifyAllDemo {
    static final Object lock = new Object();
    static boolean code = false;

    public static void main(String[] args){
        new Thread(() -> {
            synchronized (lock){
                System.out.println("是否可执行" + code);
                while (!code){
                    System.out.println("不可执行,先等等");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("是否可执行---" + code);
                if (code){
                    System.out.println("可以干活了-----");
                } else {
                    System.out.println("还是不行====");
                }
            }
        },"bodyzxy").start();

        new Thread(() -> {
            synchronized (lock){
//                code = true;
                System.out.println("可以执行了-=-=-==" + code);
                lock.notify();
            }
        },"让你执行的").start();
    }
}
