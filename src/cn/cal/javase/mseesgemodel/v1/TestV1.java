package cn.cal.javase.mseesgemodel.v1;

public class TestV1 {

    public static void main(String[] args) {
        ProducerV1 producerV1 = new ProducerV1("v1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    producerV1.product("I AM PRODUCER NUM = " + i);
                    sleep();
                }
            }

            private void sleep() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        ConsumerV1 consumerV1 = new ConsumerV1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                consumerV1.consum("v1");
            }
        }).start();
    }
}
