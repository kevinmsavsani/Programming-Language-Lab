package task3.trafficLightSystem;

import org.javatuples.Quartet;

import java.util.Date;

public class TimeThread extends Thread {

    @Override
    public void run() {
        while(true) {
            Date date = new Date();
            Constant.programTime = date.getTime()/1000;
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
