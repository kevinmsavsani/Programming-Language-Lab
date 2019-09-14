package task2.coldDrinkManufacturing;

public class PackagingThread extends Thread {
    private Packaging packaging;

    PackagingThread(Packaging packaging,String name){
        this.packaging = packaging;
        setName(name);
    }

    @Override
    public void run(){
        while(true) {
            if (Constant.observationTime<SynchronizedCounter.getTimeCounterValue()+2 || (Constant.totalB1Bottles == SynchronizedCounter.getGodownB1Value() && Constant.totalB2Bottles == SynchronizedCounter.getGodownB2Value())) {
                stop();
            }
            if (SynchronizedCounter.getPackingTimeCounterValue()<=SynchronizedCounter.getTimeCounterValue()) {
                int bottleType = packaging.packageBottle();
                if (bottleType >= 0) {
                    SynchronizedCounter.incrementPackingTimeCounter();
                    SynchronizedCounter.updateTimeCounter();
                }
            }
        }
    }
}
