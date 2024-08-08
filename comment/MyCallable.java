package comment;

import java.util.concurrent.Callable;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/8 17:08
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName() + "->" + "Hello word";
    }
}
