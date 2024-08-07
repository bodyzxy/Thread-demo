package src;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/6 13:45
 */
public class CompletableFutureApi2Demo {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            return 1;
        }, executorService).thenApply(f -> {
            return f + 2;
        }).thenAccept(r -> {
            System.out.println(r);
        });
        executorService.shutdown(); //执行完后自动关闭线程池
    }
}
