package task2.coldDrinkManufacturing;

public class Packaging {
    int counter;
    public int packageBottle() {
        if (Constant.trayPackagingInput == 1 && Constant.packagingB1.size() > 0)
        {
            SynchronizedCounter.incrementGodownB1();
            Constant.packagingB1.remove();
            Constant.trayPackagingInput = 2;
            Constant.packagedB1Bottles++;
            return 3;
        }
        if (Constant.trayPackagingInput == 2 && Constant.packagingB2.size() > 0)
        {
            SynchronizedCounter.incrementGodownB2();
            Constant.packagingB2.remove();
            Constant.trayPackagingInput = 1;
            Constant.packagedB2Bottles++;
            return 4;
        }
        if (Constant.trayPackagingInput == 2 && Constant.packagingB1.size() > 0)
        {
            SynchronizedCounter.incrementGodownB1();
            Constant.packagingB1.remove();
            Constant.trayPackagingInput = 1;
            Constant.packagedB1Bottles++;
            return 3;
        }
        if (Constant.trayPackagingInput == 1 && Constant.packagingB2.size() > 0)
        {
            SynchronizedCounter.incrementGodownB2();
            Constant.packagingB2.remove();
            Constant.trayPackagingInput = 2;
            Constant.packagedB2Bottles++;
            return 4;
        }
        if (Constant.sealing.size() == 2){
            SynchronizedCounter.updatePackingTimeCounter();
            SynchronizedCounter.updateTimeCounter();
            return -1;
        }
        if(Constant.unfinishedTrayPackagingInput == 1) {
            if (SynchronizedCounter.getUnFinishedTrayB1Value() > 0) {
                counter = SynchronizedCounter.decrementUnFinishedTrayB1Value();
                Constant.unfinishedTrayPackagingInput = 2;
                if(counter >= 0){
                    Constant.sealing.add(1);
                    Constant.packagedB1Bottles++;
                } else {
                    return packageBottle();
                }
                return 1;
            } else if (SynchronizedCounter.getUnFinishedTrayB2Value() > 0) {
                counter = SynchronizedCounter.decrementUnFinishedTrayB2Value();
                Constant.unfinishedTrayPackagingInput = 2;
                if(counter >= 0){
                    Constant.sealing.add(2);
                    Constant.packagedB2Bottles++;
                } else {
                    return packageBottle();
                }
                return 2;
            }
        }
        if(Constant.unfinishedTrayPackagingInput == 2){
            if (SynchronizedCounter.getUnFinishedTrayB2Value() > 0) {
                counter = SynchronizedCounter.decrementUnFinishedTrayB2Value();
                Constant.unfinishedTrayPackagingInput = 1;
                if(counter >= 0){
                    Constant.sealing.add(2);
                    Constant.packagedB2Bottles++;
                } else {
                    return packageBottle();
                }
                return 2;
            } else if (SynchronizedCounter.getUnFinishedTrayB1Value() > 0) {
                counter = SynchronizedCounter.decrementUnFinishedTrayB1Value();
                Constant.unfinishedTrayPackagingInput = 1;
                if(counter >= 0){
                    Constant.sealing.add(1);
                    Constant.packagedB1Bottles++;
                } else {
                    return packageBottle();
                }
                return 1;
            }
        }
        return 0;
    }
}
