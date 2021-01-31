package cn.cal.javase.mq;

import java.util.concurrent.ConcurrentHashMap;

public class ExChange {

    private ConcurrentHashMap<String, Queue> exchange;

    public ExChange() {
        this.exchange = new ConcurrentHashMap<>();
        exchange.put("/", new Queue(true));
    }

    public ConcurrentHashMap<String, Queue> getExchange() {
        return exchange;
    }

    public void setExchange(ConcurrentHashMap<String, Queue> exchange) {
        this.exchange = exchange;
    }

    public Queue getQueue(String routingKey) {
        Queue queue = this.exchange.get(routingKey);
        if(queue == null) {
            queue = new Queue(true);
            exchange.put(routingKey, queue);
        }
        return exchange.get(routingKey);
    }
}
