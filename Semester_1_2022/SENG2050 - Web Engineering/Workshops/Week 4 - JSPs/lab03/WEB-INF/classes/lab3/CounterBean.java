package lab3;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterBean {
    private AtomicInteger counter;

    public CounterBean() {
        this.counter = new AtomicInteger(0);
    }

    public int getNextValue() {
        return this.counter.incrementAndGet();
    }
}
