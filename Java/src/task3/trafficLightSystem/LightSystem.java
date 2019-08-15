package task3.trafficLightSystem;

public class LightSystem {

    private void startSystem() {
        TrafficLight trafficLight = new TrafficLight();
        trafficLight.start();

        User user = new User();
        user.start();
    }

    public static void main(String[]args){
        LightSystem lightSystem = new LightSystem();
        lightSystem.startSystem();
    }
}
