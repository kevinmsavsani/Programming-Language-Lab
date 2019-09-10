package task2.coldDrinkManufacturing;

import java.util.concurrent.atomic.AtomicInteger;

public class GodownCounter {
    private static final AtomicInteger counterB1 = new AtomicInteger(0);
    private static final AtomicInteger counterB2 = new AtomicInteger(0);

    public static int getB1Value() {
        return counterB1.get();
    }
    public static void incrementB1() {
        while(true) {
            int existingValue = getB1Value();
            int newValue = existingValue + 1;
            if(counterB1.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }
    public static int getB2Value() {
        return counterB2.get();
    }
    public static void incrementB2() {
        while(true) {
            int existingValue = getB2Value();
            int newValue = existingValue + 1;
            if(counterB2.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }
}