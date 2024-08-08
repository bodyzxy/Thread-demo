package src;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/8 19:41
 */
public class TwoPhaseTerminationDemo {
    public static void main(String[] args) throws Exception{
        TwoPhaseTermination two = new TwoPhaseTermination();
        two.start();
        Thread.sleep(1000);
        two.stop();
    }

    static class TwoPhaseTermination {
        private Thread thread;

        public void start() {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        Thread current = Thread.currentThread();
                        if (current.isInterrupted()){
                            System.out.println("后置处理");
                            break;
                        }
                        try{
                            Thread.sleep(100);
                            System.out.println("执行监控记录");
                        } catch (Exception e) {
                            e.printStackTrace();
                            thread.interrupt();
                        }
                    }
                }
            });
            thread.start();
        }

        // 停止监控
        public void stop() {
            thread.interrupt();
        }
    }
}
