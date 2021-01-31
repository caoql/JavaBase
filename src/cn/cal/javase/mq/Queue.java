package cn.cal.javase.mq;

import java.util.AbstractCollection;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Queue {

    private ConcurrentLinkedQueue queue;
    private ConcurrentLinkedDeque deque;
    private boolean multiProducer;

    public Queue(boolean multiProducer) {
        if(multiProducer) {
            this.deque = new ConcurrentLinkedDeque();
        }else {
            this.queue = new ConcurrentLinkedQueue();
        }
        this.multiProducer = multiProducer;
    }

    public AbstractCollection getQueue() {
        if(multiProducer) {
            return deque;
        } else {
            return queue;
        }
    }

    public void setQueue(ConcurrentLinkedQueue queue) {
        this.queue = queue;
    }

    public void setDeque(ConcurrentLinkedDeque deque) {
        this.deque = deque;
    }
}
