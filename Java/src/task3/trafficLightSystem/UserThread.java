package task3.trafficLightSystem;

import org.javatuples.Quartet;

import java.util.Date;

public class UserThread extends Thread {
    private long counter = 0;
    @Override
    public void run() {
        counter = (Constant.programTime - Constant.startTime)/1000;
        while (true) {
            for (Quartet<Integer, String, String,Integer> user : Constant.allUser)
            {
                if ( user.getValue3() == counter) {
                    User userInfo = new User(user.getValue0(), user.getValue1(), user.getValue2(), user.getValue3());
                    userInfo.start();
                    System.out.println("user added");
                }
            }
            while (true){
                if(counter < (Constant.programTime - Constant.startTime)/1000){
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
