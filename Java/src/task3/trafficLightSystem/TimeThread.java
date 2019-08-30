package task3.trafficLightSystem;

import org.javatuples.Quartet;

import java.util.Date;

public class TimeThread extends Thread {

    @Override
    public void run() {
        while(true) {
            Date date = new Date();
            Constant.programTime = date.getTime();
            for (Quartet<Integer, String, String, Integer> user : Constant.userDetails) {
                if (user.getValue3() == (Constant.programTime - Constant.startTime)/1000) {
                    User userInfo = new User(user.getValue0(), directionWaitTime(user.getValue1(), user.getValue2()));
                    userInfo.start();
                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
}
