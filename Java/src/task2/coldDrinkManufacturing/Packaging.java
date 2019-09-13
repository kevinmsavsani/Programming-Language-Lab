package task2.coldDrinkManufacturing;

import static java.lang.Thread.sleep;

public class Packaging {

    public int pickBottle() {

        if(Constant.packagingTimeTaken == 0){
            if (Constant.unfinishedTrayB1.size() > 0) {
                Constant.sealing.add(Constant.unfinishedTrayB1.remove());
                Constant.unfinishedTrayPackagingInput = 2;
                Constant.packagedB1Bottles++;
                return 1;
            } else if(Constant.unfinishedTrayB2.size() > 0) {
                Constant.sealing.add(Constant.unfinishedTrayB2.remove());
                Constant.unfinishedTrayPackagingInput = 2;
                Constant.packagedB2Bottles++;
                return 2;
            }
            return 0;
        }
        if (Constant.packagingB1.size() > 0)
        {
            GodownCounter.incrementB1();
            Constant.godownB1.add(Constant.packagingB1.remove());
            Constant.trayPackagingInput = 2;
            Constant.packagedB1Bottles++;
            return 3;
        }
        if (Constant.packagingB2.size() > 0)
        {
            GodownCounter.incrementB2();
            Constant.godownB2.add(Constant.packagingB2.remove());
            Constant.trayPackagingInput = 1;
            Constant.packagedB2Bottles++;
            return 4;
        }
        if (Constant.sealing.size() == 2){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return pickBottle();
        }
        if(Constant.unfinishedTrayPackagingInput == 1) {
            if (Constant.unfinishedTrayB1.size() > 0) {
                Constant.sealing.add(Constant.unfinishedTrayB1.remove());
                Constant.unfinishedTrayPackagingInput = 2;
                Constant.packagedB1Bottles++;
                return 1;
            } else if (Constant.unfinishedTrayB2.size() > 0) {
                Constant.sealing.add(Constant.unfinishedTrayB2.remove());
                Constant.unfinishedTrayPackagingInput = 2;
                Constant.packagedB2Bottles++;
                return 2;
            }
        }
        if(Constant.unfinishedTrayPackagingInput == 2){
            if (Constant.unfinishedTrayB2.size() > 0) {
                Constant.sealing.add(Constant.unfinishedTrayB2.remove());
                Constant.unfinishedTrayPackagingInput = 1;
                Constant.packagedB2Bottles++;
                return 2;
            } else if (Constant.unfinishedTrayB1.size() > 0) {
                Constant.sealing.add(Constant.unfinishedTrayB1.remove());
                Constant.unfinishedTrayPackagingInput = 1;
                Constant.packagedB1Bottles++;
                return 1;
            }
        }
        return 0;
    }
}
