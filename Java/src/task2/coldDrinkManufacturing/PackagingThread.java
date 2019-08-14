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
            int bottleType=packaging.pickBottle();
            Constant.packagingTimeTaken+=2;
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(Constant.packagingTimeTaken+2 > Constant.observationTime) {
                // Stop the thread
                System.out.println("Thread " + getName() + " Stopped!");
                stop();
            }
        }
    }
}
