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
            int bottleType=sealing.pickBottle();
            Constant.sealingTimeTaken+=3;
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(Constant.sealingTimeTaken+3 > Constant.observationTime || Constant.packagingTimeTaken == Constant.observationTime || bottleType == -1) {
                // Stop the thread
                //System.out.println("Thread " + getName() + " Stopped!");
                try {
                    sleep(abs(Constant.observationTime-Constant.sealingTimeTaken));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Constant.sealingTimeTaken = Constant.observationTime;
                stop();
            }
        }
    }
}
