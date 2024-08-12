package src;

import model.Message;

import java.util.LinkedList;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/12 11:01
 */
public class UpdateTraditionalProducerConsumer {
    public static void main(String[] args){
        MessageQueue messageQueue = new MessageQueue(2);

        for (int i = 0; i < 2; i++){
            int id = 1;
            new Thread(() -> {
                try {
                    messageQueue.put(new Message(id,"值"+id));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },"生产者" + id).start();
        }

        new Thread(() ->{
            while (true){
                try {
                    Thread.sleep(1000);
                    Message message = messageQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"消费者").start();
    }
}

//消息队列
class MessageQueue{
    private LinkedList<Message> queue = new LinkedList<>();
    //队列容量
    private int size;

    public MessageQueue(int size){
        this.size = size;
    }

    //存入信息
    public void put(Message message) throws InterruptedException {
        synchronized (queue){
            while (queue.size() == size){
                System.out.println(Thread.currentThread().getName() + ":队列为已满，生产者线程等待");
                queue.wait();
            }
            queue.addLast(message);
            System.out.println(Thread.currentThread().getName() + ":已生产消息--" +message);
            queue.notifyAll();
        }
    }

    public Message take() throws InterruptedException {
        synchronized (queue){
            while (queue.isEmpty()){
                System.out.println(Thread.currentThread().getName() + ":队列为空，消费者线程等待");
                queue.wait();
            }
            Message message = queue.removeFirst();
            System.out.println(Thread.currentThread().getName() + "：已消费消息--" + message);
            queue.notifyAll();
            return message;
        }
    }
}
