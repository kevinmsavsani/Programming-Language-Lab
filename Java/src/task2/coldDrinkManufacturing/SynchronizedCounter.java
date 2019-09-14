package task2.coldDrinkManufacturing;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedCounter {
    private static final AtomicInteger goDownCounterB1 = new AtomicInteger(0);
    private static final AtomicInteger goDownCounterB2 = new AtomicInteger(0);
    private static final AtomicInteger unFinishedTrayB1 = new AtomicInteger(Constant.totalB1Bottles);
    private static final AtomicInteger unFinishedTrayB2 = new AtomicInteger(Constant.totalB2Bottles);
    private static final AtomicInteger timeCounter = new AtomicInteger(0);
    private static final AtomicInteger packingTimeCounter = new AtomicInteger(0);
    private static final AtomicInteger sealingTimeCounter = new AtomicInteger(0);


    public static int getB1Value() {
        return goDownCounterB1.get();
    }
    public static void incrementB1() {
        while(true) {
            int existingValue = getB1Value();
            int newValue = existingValue + 1;
            if(goDownCounterB1.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    public static int getB2Value() {
        return goDownCounterB2.get();
    }
    public static void incrementB2() {
        while(true) {
            int existingValue = getB2Value();
            int newValue = existingValue + 1;
            if(goDownCounterB2.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    public static int getUnFinishedTrayB1Value() {
        return unFinishedTrayB1.get();
    }
    public static int decrementUnFinishedTrayB1Value() {
        while(true) {
            int existingValue = getUnFinishedTrayB1Value();
            int newValue = existingValue - 1;
            if(unFinishedTrayB1.compareAndSet(existingValue, newValue)) {
                return newValue;
            }
        }
    }
    public static int getUnFinishedTrayB2Value() {
        return unFinishedTrayB2.get();
    }
    public static int decrementUnFinishedTrayB2Value() {
        while(true) {
            int existingValue = getUnFinishedTrayB2Value();
            int newValue = existingValue - 1;
            if(unFinishedTrayB2.compareAndSet(existingValue, newValue)) {
                return newValue;
            }
        }
    }

    public static int getTimeCounterValue() {
        return timeCounter.get();
    }
    public static void updateTimeCounter() {
        while(true) {
            int existingValue = getTimeCounterValue();
            int newValue = Math.min(getPackingTimeCounterValue(),getSealingTimeCounterValue());

            if(timeCounter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    public static int getPackingTimeCounterValue() {
        return packingTimeCounter.get();
    }
    public static void incrementPackingTimeCounter() {
        while(true) {
            int existingValue = getPackingTimeCounterValue();
            int newValue = existingValue + 2;

            if(packingTimeCounter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }
    public static void updatePackingTimeCounter() {
        while(true) {
            int existingValue = getPackingTimeCounterValue();
            int newValue = getSealingTimeCounterValue();

            if(packingTimeCounter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    public static int getSealingTimeCounterValue() {
        return sealingTimeCounter.get();
    }
    public static void incrementSealingTimeCounter() {
        while(true) {
            int existingValue = getSealingTimeCounterValue();
            int newValue = existingValue + 3;

            if(sealingTimeCounter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }
    public static void updateSealingTimeCounter() {
        while(true) {
            int existingValue = getSealingTimeCounterValue();
            int newValue = getPackingTimeCounterValue();

            if(sealingTimeCounter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

}