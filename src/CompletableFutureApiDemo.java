package src;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/6 13:51
 */
public class CompletableFutureApiDemo {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() ->{
            try{
                System.out.println("A come in");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "completableFuture";
        });

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try{
                System.out.println("B come in");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return "completableFuture";
        });

        CompletableFuture<String> result = completableFuture.applyToEither(completableFuture1, f ->{
            return f + "is winner";
        });

        System.out.println(Thread.currentThread().getName() + "---------winner:" + result.join());
    }
}
