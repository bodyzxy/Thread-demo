package src;

import java.sql.Time;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/6 14:42
 */
public class CompletableFutureApi3Demo {
    public static void main(String[] args){
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("The first Thread come.");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return 2;
        });

        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() ->{
            try{
                System.out.println("The second Thread come.");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return 2;
        });

        CompletableFuture<Integer> completableFuture2 = completableFuture.thenCombine(completableFuture1,(x,y) ->{
            System.out.println("The third Thread come.");
            return x + y;
        });

        System.out.println(completableFuture2.join());
    }
}
