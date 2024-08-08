package src;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/8 19:18
 */
public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException, Exception{
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");

        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
        System.out.println("状态:  " + t1.isInterrupted());


        Thread t2 = new Thread(() -> {
            while (true){
                Thread current = Thread.currentThread();
                boolean interrupted = current.isInterrupted();
                if(interrupted){
                    System.out.println("打断状态: " + interrupted);
                    break;
                }

            }
        },"t2");
        t2.start();
        Thread.sleep(1000);
        t2.interrupt();
        System.out.println("打断状态2: " + t2.isInterrupted());
    }
}
