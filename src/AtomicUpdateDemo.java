package src;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/15 14:43
 */
public class AtomicUpdateDemo {
    private volatile int file;
    public static void main(String[] args) {
        AtomicIntegerFieldUpdater fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(AtomicUpdateDemo.class, "file");

        AtomicUpdateDemo atomicUpdateDemo = new AtomicUpdateDemo();

        fieldUpdater.compareAndSet(atomicUpdateDemo,0,2);
        System.out.println(atomicUpdateDemo.file);
    }
}
