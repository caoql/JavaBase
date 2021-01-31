package cn.cal.javase.mq;

public class Consumer {

    public static void consumer(int i) {
        Channel channel = Context.getChannel("exchange", "test");
        channel.receive(i);
    }

}
