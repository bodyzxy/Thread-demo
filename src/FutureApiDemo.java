package src;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Future是异步的所以当我们start之后程序会继续执行，所以需要在循环语句中设置时间等待
 * 线程的启动
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/6 10:31
 */public class FutureApiDemo {
     public static void main(String[] args) throws ExecutionException, InterruptedException {
         FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "-----------来啦");
            try{
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "结束";
         });

         Thread t1 = new Thread(futureTask,"t1");
         t1.start();

         System.out.println(Thread.currentThread().getName() + "----------正忙");

         while(true){
             if(futureTask.isDone()){
                 System.out.println(futureTask.get());
                 break;
             } else {
                 TimeUnit.MILLISECONDS.sleep(500);
                 System.out.println("正忙，越催越快");
             }
         }
     }
}
