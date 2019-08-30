package task3.trafficLightSystem;

import org.javatuples.Quartet;
import org.javatuples.Quintet;
import org.javatuples.Triplet;

import java.util.Date;

public class User extends Thread {
    private int vehicleNumber;
    private int waitTime;
    private int arrivalTime;
    private String sourceDirection;
    private String destinationDirection;
    private int index;

    public  User(int vehicleNumber, String sourceDirection, String destinationDirection, int arrivalTime){
        this.vehicleNumber = vehicleNumber-1;
        this.sourceDirection = sourceDirection;
        this.destinationDirection = destinationDirection;
        this.arrivalTime = arrivalTime;
    }

    private void getIndex() {
        for (Quartet<Integer, String, String, Integer> user : Constant.userDetails)
        {
            if ( user.getValue0() == vehicleNumber+1) {
                this.index = Constant.userDetails.indexOf(user);
            }
        }
    }

    // Add status is passed and add extra 60 sec time for car becoming more han one going from one lane to other
    private void updateUserDetails() {
        long time;
        Date date = new Date();
        Quartet<Integer, String, String,Integer> user = Constant.userDetails.get(index);
        if (user.getValue1().equalsIgnoreCase(Constant.southDirection) && user.getValue2().equalsIgnoreCase(Constant.eastDirection)) {
            if (Constant.greenTrafficlight == 1) {
                if (waitTime > ((date.getTime() / 1000) - Constant.startTrafficLightTime)){
                    time = waitTime - ((date.getTime() / 1000) - Constant.startTrafficLightTime);
                    Constant.vehicleStatus.setElementAt("Wait", index);
                    Constant.vehicleTimeStatus.setElementAt(time, index);
                } else {
                    Constant.vehicleStatus.setElementAt("Pass", index);
                    Constant.vehicleTimeStatus.setElementAt("--", index);
                }
            } else {
                time = 60 - ((date.getTime() / 1000) - Constant.startTrafficLightTime);
                if (Constant.greenTrafficlight == 2) {
                    time += 60;
                } else {
                    time += 0;
                }
                time += waitTime;
                Constant.vehicleStatus.setElementAt("Wait", index);
                Constant.vehicleTimeStatus.setElementAt(time, index);
            }
        } else if (user.getValue1().equalsIgnoreCase(Constant.westDirection) && user.getValue2().equalsIgnoreCase(Constant.southDirection)) {
            if (Constant.greenTrafficlight == 2) {
                if (waitTime > ((date.getTime() / 1000) - Constant.startTrafficLightTime)){
                    time = waitTime - ((date.getTime() / 1000) - Constant.startTrafficLightTime);
                    Constant.vehicleStatus.setElementAt("Wait", index);
                    Constant.vehicleTimeStatus.setElementAt(time, index);
                } else {
                    Constant.vehicleStatus.setElementAt("Pass", index);
                    Constant.vehicleTimeStatus.setElementAt("--", index);
                }
            } else {
                time = 60 - ((date.getTime() / 1000) - Constant.startTrafficLightTime);
                if (Constant.greenTrafficlight == 3) {
                    time += 60;
                } else {
                    time += 0;
                }
                time += waitTime;
                Constant.vehicleStatus.setElementAt("Wait", index);
                Constant.vehicleTimeStatus.setElementAt(time, index);
            }
        }
        else if (user.getValue1().equalsIgnoreCase(Constant.eastDirection) && user.getValue2().equalsIgnoreCase(Constant.westDirection)) {
            if (Constant.greenTrafficlight == 3) {
                if (waitTime > ((date.getTime() / 1000) - Constant.startTrafficLightTime)){
                    time = waitTime - ((date.getTime() / 1000) - Constant.startTrafficLightTime);
                    Constant.vehicleStatus.setElementAt("Wait", index);
                    Constant.vehicleTimeStatus.setElementAt(time, index);
                } else {
                    Constant.vehicleStatus.setElementAt("Pass", index);
                    Constant.vehicleTimeStatus.setElementAt("--", index);
                }
            } else {
                time = 60 - ((date.getTime() / 1000) - Constant.startTrafficLightTime);
                if (Constant.greenTrafficlight == 1) {
                    time += 60;
                } else {
                    time += 0;
                }
                time += waitTime;
                Constant.vehicleStatus.setElementAt("Wait", index);
                Constant.vehicleTimeStatus.setElementAt(time, index);
            }
        }
    }

    private static int directionWaitTime(String item1, String item2) {
        if(item1.equalsIgnoreCase(Constant.southDirection) && item2.equalsIgnoreCase(Constant.eastDirection)){
            return Constant.southEastWait++;
        }
        else if(item1.equalsIgnoreCase(Constant.westDirection) && item2.equalsIgnoreCase(Constant.southDirection)){
            return Constant.westSouthWait++;
        }
        else if(item1.equalsIgnoreCase(Constant.eastDirection) && item2.equalsIgnoreCase(Constant.westDirection)){
            return Constant.eastWestWait++;
        }
        else {
            return 0;
        }
    }

    @Override
    public void run() {
        while (true) {
            if (arrivalTime == (Constant.programTime - Constant.startTime)/1000) {
                Constant.userDetails.add(new Quartet<>(vehicleNumber+1,sourceDirection,destinationDirection,arrivalTime));
                Constant.vehicleStatus.add("Pass");
                Constant.vehicleTimeStatus.add("--");
                this.waitTime = directionWaitTime(sourceDirection,destinationDirection)*6;
                getIndex();
                break;
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (true) {
            updateUserDetails();
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
