package task3.trafficLightSystem;

import java.util.Date;

public class TrafficLight extends Thread {
    @Override
    public void run() {
        Date startDate = new Date();
        Constant.startTrafficLightTime = startDate.getTime()/1000;
        while(true)
        {
            Date date = new Date();
            if((date.getTime()/1000)-Constant.startTrafficLightTime >=60) {
                if (Constant.greenTrafficlight == 3) {
                    Constant.startTrafficLightTime = (date.getTime() / 1000);
                    Constant.greenTrafficlight = 1;
                } else {
                    Constant.startTrafficLightTime = (date.getTime() / 1000);
                    Constant.greenTrafficlight++;
                }
            }
        }
    }
}
