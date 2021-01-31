package cn.cal.javase.mq;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Channel {

    private ExChange exChange;
    private String routingKey;

    public Channel(String exChangeName, String routingKey) {
        this.exChange = Context.getExchanges(exChangeName);
        this.routingKey = routingKey;
    }

    public Queue getQueue() {
        return exChange.getQueue(routingKey);
    }

    public void  send(String msg, int tag) {
        ConcurrentLinkedDeque deque = (ConcurrentLinkedDeque) getQueue().getQueue();
        boolean offer = deque.offer(msg);
        if(offer) {
            System.out.println(tag+"=====send success");
        }
    }

    public void receive(int tag) {
        ConcurrentLinkedDeque deque = (ConcurrentLinkedDeque) getQueue().getQueue();
        synchronized (Queue.class) {
            if(!deque.isEmpty()) {
                String poll = (String)deque.poll();
                System.out.println(tag+"=====取到数据====="+ poll);
            }
        }
    }
}
