package task2.coldDrinkManufacturing;

import static java.lang.Math.abs;

public class PackagingThread extends Thread {
    private Packaging packaging;

    PackagingThread(Packaging packaging,String name){
        this.packaging = packaging;
        setName(name);
    }

    @Override
    public void run(){
        while(true) {
            if (Constant.observationTime<SynchronizedCounter.getTimeCounterValue()+2 || (Constant.totalB1Bottles == SynchronizedCounter.getB1Value() && Constant.totalB2Bottles == SynchronizedCounter.getB2Value())) {
                stop();
            }
            if (SynchronizedCounter.getPackingTimeCounterValue()<=SynchronizedCounter.getTimeCounterValue()) {
                int bottleType = packaging.pickBottle();
                if (bottleType >= 0) {
                    SynchronizedCounter.incrementPackingTimeCounter();
                    SynchronizedCounter.updateTimeCounter();
                }
            }
            Constant.packagingTimeTaken += 2;
        }
    }
}
