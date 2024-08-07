package src;

import java.util.concurrent.*;

/**
 * CompletableFuture可以实现Future的所有功能，并且其会回调函数。
 * 其中CompletableFuture有静态构造方法含有Executor，若没有指定，则使用默认的ForkJoinPoolcommonPool（）作为它的线程池执行异步代码，如果指定线程池，则使用我们自定义的或者特别指定的线程池执行异步代码
 * 其中whenComplete是完成时调用的回调函数，exceptionally是失败时调用的回调函数
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/6 11:04
 */
public class CompletableFutureDemo {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "-----------来啦");
            int result = ThreadLocalRandom.current().nextInt(10);
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (result > 0){ //设置一些异常情况
                int i = 10 / 0;
            }
            System.out.println("=======1秒后的结果: " + result);
            return result;
        },executorService).whenComplete((v,e) -> {
            if (e == null){
                System.out.println("计算机完成任务!!!!!");
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println("异常情况:" + e.getCause() + e.getMessage());
            return null;
        });
        System.out.println(Thread.currentThread().getName() + "先去完成其他任务");
        executorService.shutdown();
    }
}
