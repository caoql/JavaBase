package cn.cal.javase.core;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class IntegerTest {
    public static void main(String[] args) {
        // 反编译看字节码
        Integer integer = 1;
        int unboxing = integer++;
    }
}


class Counter {
    private final AtomicLong counter = new AtomicLong();

    public void increase() {
        counter.incrementAndGet();
    }
}


class CompactCounter {
    private volatile long counter;
    private static final AtomicLongFieldUpdater<CompactCounter> updater = AtomicLongFieldUpdater.newUpdater(CompactCounter.class, "counter");

    public void increase() {
        updater.incrementAndGet(this);
    }
}

