package cn.cal.javase.mq;

public class MqTest {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    final int temp = i;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j < 100; j++) {
                                Producer.product(temp*100+j);
                            }
                        }
                    }).start();
                }
            }
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    final int temp = i;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j < 100; j++) {
                                Consumer.consumer(temp*100+j);
                            }
                        }
                    }).start();
                }
            }
        }).start();

    }
}
