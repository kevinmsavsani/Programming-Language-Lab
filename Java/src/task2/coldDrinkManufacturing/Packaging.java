package task2.coldDrinkManufacturing;

public class Packaging {

    public int pickBottle() {

        if(Constant.packagingTimeTaken == 0){
            if (Constant.unfinishedTrayB1Bottles > 0) {
                Constant.unfinishedTrayB1Bottles--;
                Constant.unfinishedTrayPackagingInput = 2;
                Constant.sealingTrayFirstElement = 1;
                Constant.sealingTraySize = 1;
                Constant.packagedB1Bottles++;
                return 1;
            } else if(Constant.unfinishedTrayB2Bottles > 0) {
                Constant.unfinishedTrayB2Bottles--;
                Constant.unfinishedTrayPackagingInput = 2;
                Constant.sealingTrayFirstElement = 2;
                Constant.sealingTraySize = 1;
                Constant.packagedB2Bottles++;
                return 2;
            }
            return 0;
        }
        if (Constant.packagingB1Tray > 0)
        {
            Constant.packagingB1Tray--;
            Constant.godownB1Bottles++;
            Constant.trayPackagingInput = 2;
            Constant.packagedB1Bottles++;
            return 3;
        }
        if (Constant.packagingB2Tray > 0)
        {
            Constant.packagingB2Tray--;
            Constant.godownB2Bottles++;
            Constant.trayPackagingInput = 1;
            Constant.packagedB2Bottles++;
            return 4;
        }
        if (Constant.sealingTraySize == 2){
            return pickBottle();
        }
        if(Constant.unfinishedTrayPackagingInput == 1) {
            if (Constant.unfinishedTrayB1Bottles > 0) {
                Constant.unfinishedTrayB1Bottles--;
                Constant.unfinishedTrayPackagingInput = 2;
                Constant.packagedB1Bottles++;
                if (Constant.sealingTraySize == 0) {
                    Constant.sealingTrayFirstElement = 1;
                    Constant.sealingTraySize = 1;
                    return 1;
                } else if (Constant.sealingTraySize == 1) {
                    Constant.sealingTraySecondElement = 1;
                    Constant.sealingTraySize = 2;
                    return 1;
                }
            } else if (Constant.unfinishedTrayB2Bottles > 0) {
                Constant.unfinishedTrayB2Bottles--;
                Constant.unfinishedTrayPackagingInput = 2;
                Constant.packagedB2Bottles++;
                if (Constant.sealingTraySize == 0)
                {
                    Constant.sealingTrayFirstElement = 2;
                    Constant.sealingTraySize = 1;
                    return 2;
                }
                else if (Constant.sealingTraySize == 1)
                {
                    Constant.sealingTraySecondElement = 2;
                    Constant.sealingTraySize = 2;
                    return 2;
                }
            }
        }
        if(Constant.unfinishedTrayPackagingInput == 2){
            if (Constant.unfinishedTrayB2Bottles > 0) {
                Constant.unfinishedTrayB2Bottles--;
                Constant.unfinishedTrayPackagingInput = 1;
                Constant.packagedB2Bottles++;
                if (Constant.sealingTraySize == 0) {
                    Constant.sealingTrayFirstElement = 2;
                    Constant.sealingTraySize = 1;
                    return 2;
                } else if (Constant.sealingTraySize == 1) {
                    Constant.sealingTraySecondElement = 2;
                    Constant.sealingTraySize = 2;
                    return 2;
                }
            } else if (Constant.unfinishedTrayB1Bottles > 0) {
                Constant.unfinishedTrayB1Bottles--;
                Constant.unfinishedTrayPackagingInput = 1;
                Constant.packagedB1Bottles++;
                if (Constant.sealingTraySize == 0) {
                    Constant.sealingTrayFirstElement = 1;
                    Constant.sealingTraySize = 1;
                    return 1;
                } else if (Constant.sealingTraySize == 1) {
                    Constant.sealingTraySecondElement = 1;
                    Constant.sealingTraySize = 2;
                    return 1;
                }
            }
        }
        return 0;
    }
}
