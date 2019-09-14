package task2.coldDrinkManufacturing;

public class Sealing extends Thread {
    int counter;
    public int sealBottle() {
        if (Constant.sealing.size() > 0)
        {
            int sealingTrayFirstElement = (int)Constant.sealing.remove();
            if (sealingTrayFirstElement == 1)
            {
                SynchronizedCounter.incrementGodownB1();
                Constant.sealedB1Bottles++;
                return 3;
            }
            if (sealingTrayFirstElement == 2)
            {
                SynchronizedCounter.incrementGodownB2();
                Constant.sealedB2Bottles++;
                return 4;
            }
        }
        if (Constant.packagingB1.size() == Constant.packagingB1TraySize || Constant.packagingB2.size() == Constant.packagingB2TraySize){
            SynchronizedCounter.updateSealingTimeCounter();
            SynchronizedCounter.updateTimeCounter();
            return -1;
        }
        if(Constant.unfinishedTraySealingInput == 1) {
            if (SynchronizedCounter.getUnFinishedTrayB1Value() > 0) {
                counter = SynchronizedCounter.decrementUnFinishedTrayB1Value();
                Constant.unfinishedTraySealingInput = 2;
                if(counter >= 0){
                    Constant.packagingB1.add(1);
                    Constant.sealedB1Bottles++;
                } else {
                    return sealBottle();
                }
                return 1;
            }
            if (SynchronizedCounter.getUnFinishedTrayB2Value() > 0) {
                counter = SynchronizedCounter.decrementUnFinishedTrayB2Value();
                Constant.unfinishedTraySealingInput = 2;
                if(counter >= 0){
                    Constant.packagingB2.add(2);
                    Constant.sealedB2Bottles++;
                } else {
                    return sealBottle();
                }
                return 2;
            }
        }
        if(Constant.unfinishedTraySealingInput == 2) {
            if (SynchronizedCounter.getUnFinishedTrayB2Value() > 0) {
                counter = SynchronizedCounter.decrementUnFinishedTrayB2Value();
                Constant.unfinishedTraySealingInput = 1;
                if(counter >= 0){
                    Constant.packagingB2.add(2);
                    Constant.sealedB2Bottles++;
                } else {
                    return sealBottle();
                }
                return 2;
            } else if (SynchronizedCounter.getUnFinishedTrayB1Value() > 0) {
                counter = SynchronizedCounter.decrementUnFinishedTrayB1Value();
                Constant.unfinishedTraySealingInput = 1;
                if(counter >= 0){
                    Constant.packagingB1.add(1);
                    Constant.sealedB1Bottles++;
                } else {
                    return sealBottle();
                }
                return 1;
            }
        }
        return 0;
    }
}
