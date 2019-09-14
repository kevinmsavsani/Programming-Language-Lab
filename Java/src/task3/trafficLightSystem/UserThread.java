package task3.trafficLightSystem;

import org.javatuples.Quartet;

import java.util.Date;

public class UserThread extends Thread {
    private long counter = 0;
    @Override
    public void run() {
        counter = (Constant.programTime - Constant.startTime);
        while (true) {
            for (Quartet<Integer, String, String,Integer> user : Constant.allUser)
            {
                if ( user.getValue3() == counter) {
                    User userInfo = new User(user.getValue0(), user.getValue1(), user.getValue2(), user.getValue3());
                    userInfo.start();
                }
            }
            while (true){
                if(counter < (Constant.programTime - Constant.startTime)){
                    counter++;
                    break;
                } else {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
