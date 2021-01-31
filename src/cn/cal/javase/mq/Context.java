package cn.cal.javase.mq;

import java.util.concurrent.ConcurrentHashMap;

public class Context {

    private static ConcurrentHashMap<String, ExChange> exchangeMap = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<>();

    public static Channel getChannel(String name, String routingKey) {
        Channel channel = channelMap.get(name);
        if(channel == null) {
            channel = new Channel(name, routingKey);
        }
        channelMap.put(name, channel);
        return channel;
    }

    public static ExChange getExchanges(String name) {
        ExChange exChange = exchangeMap.get(name);
        if(exChange == null) {
            exChange = new ExChange();
        }
        return exChange;
    }
}
