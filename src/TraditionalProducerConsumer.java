package src;

import model.ShareData;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/12 10:50
 */
public class TraditionalProducerConsumer {
    public static void main(String[] args){
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 5; i++){
                try {
                    shareData.increment();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        },"ts").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++){
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        },"t2").start();
    }
}
