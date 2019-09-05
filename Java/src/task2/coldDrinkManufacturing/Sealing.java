package task2.coldDrinkManufacturing;

public class Sealing extends Thread {

    public int pickBottle() {
        if(Constant.sealingTimeTaken == 0){
            if (Constant.totalB2Bottles > 0) {
                Constant.packagingB2.add(Constant.unfinishedTrayB2.remove());
                Constant.unfinishedTraySealingInput = 1;
                Constant.sealedB2Bottles++;
                return 2;
            } else if (Constant.totalB1Bottles > 0) {
                Constant.packagingB1.add(Constant.unfinishedTrayB1.remove());
                Constant.unfinishedTraySealingInput = 1;
                Constant.sealedB1Bottles++;
                return 1;
            }
            else return 0;
        }
        if (Constant.sealing.size() > 0)
        {
            int sealingTrayFirstElement = Constant.sealing.remove();
            if (sealingTrayFirstElement == 1)
            {
                Constant.godownB1.add(sealingTrayFirstElement);
                Constant.sealedB1Bottles++;
                return 3;
            }
            if (sealingTrayFirstElement == 2)
            {
                Constant.godownB2.add(sealingTrayFirstElement);
                Constant.sealedB2Bottles++;
                return 4;
            }
        }
        if (Constant.packagingB1.size() == Constant.packagingB1TraySize || Constant.packagingB2.size() == Constant.packagingB2TraySize){
            return pickBottle();
        }
        if(Constant.unfinishedTraySealingInput == 1) {
            if (Constant.unfinishedTrayB1.size() > 0) {
                Constant.packagingB1.add(Constant.unfinishedTrayB1.remove());
                Constant.unfinishedTraySealingInput = 2;
                Constant.sealedB1Bottles++;
                return 1;
            }
            if (Constant.unfinishedTrayB2.size() > 0) {
                Constant.packagingB2.add(Constant.unfinishedTrayB2.remove());
                Constant.unfinishedTraySealingInput = 2;
                Constant.sealedB2Bottles++;
                return 2;
            }
        }
        if(Constant.unfinishedTraySealingInput == 2) {
            if (Constant.unfinishedTrayB2.size() > 0) {
                Constant.packagingB2.add(Constant.unfinishedTrayB2.remove());
                Constant.unfinishedTraySealingInput = 1;
                Constant.sealedB2Bottles++;
                return 2;
            } else if (Constant.unfinishedTrayB1.size() > 0) {
                Constant.packagingB1.add(Constant.unfinishedTrayB1.remove());
                Constant.unfinishedTraySealingInput = 1;
                Constant.sealedB1Bottles++;
                return 1;
            }
        }
        return 0;
    }
}
