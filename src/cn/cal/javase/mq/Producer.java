package cn.cal.javase.mq;

public class Producer {

    public static void product(int i) {
        Channel channel = Context.getChannel("exchange", "test");
        channel.send("I AM PRODUCER", i);
    }

}
