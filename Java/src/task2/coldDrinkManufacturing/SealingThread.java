package task2.coldDrinkManufacturing;

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
            if(Constant.sealingTimeTaken+3 > Constant.observationTime) {
                // Stop the thread
                System.out.println("Thread " + getName() + " Stopped!");
                stop();
            }
        }
    }
}
