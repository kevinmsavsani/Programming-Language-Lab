package task3.trafficLightSystem;

import org.javatuples.Quartet;

public class User extends Thread {
    private int vehicleNumber;
    private long waitTime;
    private int arrivalTime;
    private String sourceDirection;
    private String destinationDirection;
    private int index;
    private int numCarWait;

    public  User(int vehicleNumber, String sourceDirection, String destinationDirection, int arrivalTime){
        this.vehicleNumber = vehicleNumber-1;
        this.sourceDirection = sourceDirection;
        this.destinationDirection = destinationDirection;
        this.arrivalTime = arrivalTime;
    }

    private void getIndex() {
        synchronized (Constant.userDetails) {
            for (Quartet<Integer, String, String, Integer> user : Constant.userDetails) {
                if (user.getValue0() == vehicleNumber + 1) {
                    this.index = Constant.userDetails.indexOf(user);
                    break;
                }
            }
        }
    }

    private void updateTime() {
        this.waitTime--;
        Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
        if (this.waitTime <= 0) {
            Constant.vehicleStatus.setElementAt("Pass", this.index);
            Constant.vehicleTimeStatus.setElementAt("--", this.index);
        }
    }

    private void updateUserDetails() {
        long time;
        Quartet<Integer, String, String,Integer> user = Constant.userDetails.get(this.index);
        time = 60 - ( Constant.programTime - Constant.startTrafficLightTime );
        if (user.getValue1().equalsIgnoreCase(Constant.southDirection) && user.getValue2().equalsIgnoreCase(Constant.eastDirection)) {
            if (Constant.greenTrafficlight == 1) {
                if ( this.waitTime > 0 ) {
                    if (time < this.waitTime + 6) {
                        long numCarExit = time/6;
                        this.numCarWait -= numCarExit;
                        long carWait = time + 120 + (this.numCarWait*6) + (((this.numCarWait-1)/10)*120);
                        this.waitTime = carWait;
                    }
                    Constant.vehicleStatus.setElementAt("Wait", this.index);
                    Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
                } else if (this.waitTime == 0 && time < 6) {
                    this.waitTime = time + 120;
                    Constant.vehicleStatus.setElementAt("Wait", this.index);
                    Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
                } else {
                    Constant.vehicleStatus.setElementAt("Pass", this.index);
                    Constant.vehicleTimeStatus.setElementAt("--", this.index);
                }
            } else {
                if (Constant.greenTrafficlight == 2) {
                    time += 60;
                } else {
                    time += 0;
                }
                this.waitTime += time;
                Constant.vehicleStatus.setElementAt("Wait", this.index);
                Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
            }
        }

        else if (user.getValue1().equalsIgnoreCase(Constant.westDirection) && user.getValue2().equalsIgnoreCase(Constant.southDirection)) {
            if (Constant.greenTrafficlight == 2) {
                if (this.waitTime > 0 ){
                    if (time < this.waitTime + 6) {
                        long numCarExit = time/6;
                        this.numCarWait -= numCarExit;
                        long carWait = time + 120 + (this.numCarWait*6) + (((this.numCarWait-1)/10)*120);
                        this.waitTime = carWait;
                    }
                    Constant.vehicleStatus.setElementAt("Wait", this.index);
                    Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
                } else if (this.waitTime == 0 && time < 6) {
                    this.waitTime = time + 120;
                    Constant.vehicleStatus.setElementAt("Wait", this.index);
                    Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
                } else {
                    Constant.vehicleStatus.setElementAt("Pass", this.index);
                    Constant.vehicleTimeStatus.setElementAt("--", this.index);
                }
            } else {
                if (Constant.greenTrafficlight == 3) {
                    time += 60;
                } else {
                    time += 0;
                }
                this.waitTime += time;
                Constant.vehicleStatus.setElementAt("Wait", this.index);
                Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
            }
        }
        else if (user.getValue1().equalsIgnoreCase(Constant.eastDirection) && user.getValue2().equalsIgnoreCase(Constant.westDirection)) {
            if (Constant.greenTrafficlight == 3) {
                if (this.waitTime > 0 ){
                    if (time < this.waitTime + 6) {
                        long numCarExit = time/6;
                        this.numCarWait -= numCarExit;
                        long carWait = time + 120 + (this.numCarWait*6) + (((this.numCarWait-1)/10)*120);
                        this.waitTime = carWait;
                    }
                    Constant.vehicleStatus.setElementAt("Wait", this.index);
                    Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
                } else if (this.waitTime == 0 && time < 6) {
                    this.waitTime = time + 120;
                    Constant.vehicleStatus.setElementAt("Wait", this.index);
                    Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
                } else {
                    Constant.vehicleStatus.setElementAt("Pass", this.index);
                    Constant.vehicleTimeStatus.setElementAt("--", this.index);
                }
            } else {
                if (Constant.greenTrafficlight == 1) {
                    time += 60;
                } else {
                    time += 0;
                }
                this.waitTime += time;
                Constant.vehicleStatus.setElementAt("Wait", this.index);
                Constant.vehicleTimeStatus.setElementAt(this.waitTime, this.index);
            }
        }
    }

    private synchronized static int directionWaitTimeAdd(String item1, String item2) {
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

    private static void directionWaitTimeRemove(String item1, String item2) {
        if(item1.equalsIgnoreCase(Constant.southDirection) && item2.equalsIgnoreCase(Constant.eastDirection)){
            Constant.southEastWait--;
        }
        else if(item1.equalsIgnoreCase(Constant.westDirection) && item2.equalsIgnoreCase(Constant.southDirection)){
            Constant.westSouthWait--;
        }
        else if(item1.equalsIgnoreCase(Constant.eastDirection) && item2.equalsIgnoreCase(Constant.westDirection)){
            Constant.eastWestWait--;
        }
    }

    @Override
    public void run() {
        synchronized (Constant.userDetails) {
            Constant.userDetails.add(new Quartet<>(vehicleNumber + 1, sourceDirection, destinationDirection, arrivalTime));
            Constant.vehicleStatus.add("Pass");
            Constant.vehicleTimeStatus.add("--");
        }
        this.numCarWait = directionWaitTimeAdd(this.sourceDirection,this.destinationDirection);
        this.waitTime = (this.numCarWait*6) + (((this.numCarWait)/10)*120) ;
        getIndex();
        updateUserDetails();
        while (true) {
            updateTime();
            if (Constant.vehicleStatus.get(this.index).equals("Pass")){
                try {
                    sleep(6000);
                    Constant.vehicleStatus.setElementAt("Passed", this.index);
                    Constant.vehicleTimeStatus.setElementAt("--", this.index);
                    directionWaitTimeRemove(this.sourceDirection,this.destinationDirection);
                    stop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
