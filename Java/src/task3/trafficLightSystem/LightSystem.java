package task3.trafficLightSystem;

import java.util.Date;

public class LightSystem {

    private void startSystem() {
        Date date = new Date();

        Constant.startTime = date.getTime();
        TrafficLight trafficLight = new TrafficLight();
        trafficLight.start();

        TimeThread timeThread = new TimeThread();
        timeThread.start();

        UserThread userThread = new UserThread();
        userThread.start();

        UserGUI userGUI = new UserGUI();
        userGUI.start();

        TrafficLightGui trafficLightGui = new TrafficLightGui();
        trafficLightGui.setVisible(true);

    }

    public static void main(String[]args){
        LightSystem lightSystem = new LightSystem();
        lightSystem.startSystem();
    }
}
