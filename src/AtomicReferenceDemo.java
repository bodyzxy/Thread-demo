package src;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/15 14:24
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        String a = new String("a");

        AtomicReference<String> atomicReference = new AtomicReference<>();

        atomicReference.set(a);

        while (true){
            String b = new String("b");
            if(atomicReference.compareAndSet(a,b)){
                break;
            }
        }
        System.out.println(atomicReference.get());
    }
}
