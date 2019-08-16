package task3.trafficLightSystem;

import org.javatuples.Quintet;
import org.javatuples.Triplet;

import java.util.Date;

public class User extends Thread {
    private int vehicleNumber;
    private int waitTime;

    public  User(int vehicleNumber, int waitTime){
        this.vehicleNumber = vehicleNumber-1;
        this.waitTime = waitTime*6;
    }

    private void updateUserDetails() {
        long time;
        Date date = new Date();
        Triplet<Integer, String, String> user = Constant.userDetails.get(vehicleNumber);
        if (user.getValue1().equalsIgnoreCase(Constant.southDirection) && user.getValue2().equalsIgnoreCase(Constant.eastDirection)) {
            if (Constant.greenTrafficlight == 1) {
                if (waitTime > ((date.getTime() / 1000) - Constant.startTrafficLightTime)){
                    time = waitTime - ((date.getTime() / 1000) - Constant.startTrafficLightTime);
                    Constant.vehicleStatus.setElementAt("Wait", vehicleNumber);
                    Constant.vehicleTimeStatus.setElementAt(time, vehicleNumber);
                } else {
                    Constant.vehicleStatus.setElementAt("Pass", vehicleNumber);
                    Constant.vehicleTimeStatus.setElementAt("--", vehicleNumber);
                }
            } else {
                time = 60 - ((date.getTime() / 1000) - Constant.startTrafficLightTime);
                if (Constant.greenTrafficlight == 2) {
                    time += 60;
                } else {
                    time += 0;
                }
                time += waitTime;
                Constant.vehicleStatus.setElementAt("Wait", vehicleNumber);
                Constant.vehicleTimeStatus.setElementAt(time, vehicleNumber);
            }
        } else if (user.getValue1().equalsIgnoreCase(Constant.westDirection) && user.getValue2().equalsIgnoreCase(Constant.southDirection)) {
            if (Constant.greenTrafficlight == 2) {
                if (waitTime > ((date.getTime() / 1000) - Constant.startTrafficLightTime)){
                    time = waitTime - ((date.getTime() / 1000) - Constant.startTrafficLightTime);
                    Constant.vehicleStatus.setElementAt("Wait", vehicleNumber);
                    Constant.vehicleTimeStatus.setElementAt(time, vehicleNumber);
                } else {
                    Constant.vehicleStatus.setElementAt("Pass", vehicleNumber);
                    Constant.vehicleTimeStatus.setElementAt("--", vehicleNumber);
                }
            } else {
                time = 60 - ((date.getTime() / 1000) - Constant.startTrafficLightTime);
                if (Constant.greenTrafficlight == 3) {
                    time += 60;
                } else {
                    time += 0;
                }
                time += waitTime;
                Constant.vehicleStatus.setElementAt("Wait", vehicleNumber);
                Constant.vehicleTimeStatus.setElementAt(time, vehicleNumber);
            }
        }
        else if (user.getValue1().equalsIgnoreCase(Constant.eastDirection) && user.getValue2().equalsIgnoreCase(Constant.westDirection)) {
            if (Constant.greenTrafficlight == 3) {
                if (waitTime > ((date.getTime() / 1000) - Constant.startTrafficLightTime)){
                    time = waitTime - ((date.getTime() / 1000) - Constant.startTrafficLightTime);
                    Constant.vehicleStatus.setElementAt("Wait", vehicleNumber);
                    Constant.vehicleTimeStatus.setElementAt(time, vehicleNumber);
                } else {
                    Constant.vehicleStatus.setElementAt("Pass", vehicleNumber);
                    Constant.vehicleTimeStatus.setElementAt("--", vehicleNumber);
                }
            } else {
                time = 60 - ((date.getTime() / 1000) - Constant.startTrafficLightTime);
                if (Constant.greenTrafficlight == 1) {
                    time += 60;
                } else {
                    time += 0;
                }
                time += waitTime;
                Constant.vehicleStatus.setElementAt("Wait", vehicleNumber);
                Constant.vehicleTimeStatus.setElementAt(time, vehicleNumber);
            }
        }
    }


    @Override
    public void run() {
        while (true) {
            updateUserDetails();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
