package task3.trafficLightSystem;

public class User extends Thread {

    @Override
    public void run() {
        UserInterface.generateGui();
        //UserInterface.generateStatusGui();

    }
}
