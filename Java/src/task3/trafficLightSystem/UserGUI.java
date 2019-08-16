package task3.trafficLightSystem;

public class UserGUI extends Thread {

    @Override
    public void run() {
        UserInterface.generateGui();
        //UserInterface.generateStatusGui();

    }
}
