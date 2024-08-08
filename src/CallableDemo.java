package src;

import comment.MyCallable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/8 17:01
 */
public class CallableDemo {

    public static void main(String[] args){
        Callable<String> call = new MyCallable();
        FutureTask<String> task = new FutureTask<>(call);
        Thread t1 = new Thread(task);
        t1.start();

        try{
            String s = task.get();
            System.out.println(s);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
