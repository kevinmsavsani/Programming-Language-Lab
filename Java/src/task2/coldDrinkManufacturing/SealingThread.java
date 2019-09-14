package task2.coldDrinkManufacturing;

import static java.lang.Math.abs;

public class SealingThread extends Thread {

    private Sealing sealing;

    SealingThread(Sealing sealing, String name){
        this.sealing = sealing;
        setName(name);
    }

    @Override
    public void run(){
        while (true) {
            if (Constant.observationTime<SynchronizedCounter.getTimeCounterValue()+3 || (Constant.totalB1Bottles == SynchronizedCounter.getB1Value() && Constant.totalB2Bottles == SynchronizedCounter.getB2Value())) {
                stop();
            }
            if (SynchronizedCounter.getSealingTimeCounterValue()<=SynchronizedCounter.getTimeCounterValue()) {
                int bottleType = sealing.pickBottle();
                if (bottleType >=0) {
                    SynchronizedCounter.incrementSealingTimeCounter();
                    SynchronizedCounter.updateTimeCounter();
                }
            }
            Constant.sealingTimeTaken += 2;

        }
    }
}
