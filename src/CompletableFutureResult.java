package src;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/6 13:35
 */
public class CompletableFutureResult {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        },executorService).thenApply(f -> {
            System.out.println("222");
            return f + 2;
        }).handle((f,e) -> {
            System.out.println("333");
//            int i = 10 / 0;
            return f + 2;
        }).whenComplete((v,e) ->{
            if (e == null) {
                System.out.println("----计算结果" + v);
            }
        }).exceptionally(e ->{
            e.printStackTrace();
            System.out.println(e.getCause());
            return null;
        });
        System.out.println(Thread.currentThread().getName() + "------主线程先去做其他事情");
    }
}
