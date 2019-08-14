package task2.coldDrinkManufacturing;

public class Sealing extends Thread {

    public int pickBottle() {
        if(Constant.sealingTimeTaken == 0){
            Constant.unfinishedTrayB2Bottles--;
            Constant.unfinishedTraySealingInput = 1;
            Constant.packagingB2Tray++;
            Constant.sealedB2Bottles++;
            return 2;
        }
        if (Constant.sealingTraySize > 0)
        {
            if (Constant.sealingTraySize == 1)
            {
                if (Constant.sealingTrayFirstElement == 1)
                {
                    Constant.sealingTraySize--;
                    Constant.sealingTrayFirstElement = 0;
                    Constant.godownB1Bottles++;
                    Constant.sealedB1Bottles++;
                    return 3;
                }
                if (Constant.sealingTrayFirstElement == 2)
                {
                    Constant.sealingTraySize--;
                    Constant.sealingTrayFirstElement = 0;
                    Constant.godownB2Bottles++;
                    Constant.sealedB2Bottles++;
                    return 4;
                }
            }
            else if (Constant.sealingTraySize == 2)
            {
                if (Constant.sealingTrayFirstElement == 1)
                {
                    Constant.sealingTraySize--;
                    Constant.sealingTrayFirstElement = Constant.sealingTraySecondElement;
                    Constant.godownB1Bottles++;
                    Constant.sealedB1Bottles++;
                    return 3;
                }
                if (Constant.sealingTrayFirstElement == 2)
                {
                    Constant.sealingTraySize--;
                    Constant.sealingTrayFirstElement = Constant.sealingTraySecondElement;
                    Constant.godownB2Bottles++;
                    Constant.sealedB2Bottles++;
                    return 4;
                }
            }
        }
        if (Constant.packagingB1Tray == Constant.packagingB1TraySize || Constant.packagingB2Tray == Constant.packagingB2TraySize){
            return pickBottle();
        }
        if(Constant.unfinishedTraySealingInput == 1){
            Constant.unfinishedTrayB1Bottles--;
            Constant.unfinishedTraySealingInput = 2;
            Constant.packagingB1Tray++;
            Constant.sealedB1Bottles++;
            return 1;
        }
        if(Constant.unfinishedTraySealingInput == 2){
            Constant.unfinishedTrayB2Bottles--;
            Constant.unfinishedTraySealingInput = 1;
            Constant.packagingB2Tray++;
            Constant.sealedB2Bottles++;
            return 2;
        }
        return 0;
    }
}
