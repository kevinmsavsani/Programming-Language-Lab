package task3.trafficLightSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.javatuples.Triplet;

public class Constant {
    public static long startTrafficLightTime;
    public static int greenTrafficlight = 1;
    public static String southDirection = "South";
    public static String westDirection = "West";
    public static String eastDirection = "East";
    public static List<Triplet<Integer, String, String>> userDetails = new ArrayList<>();
    public static List<String> directions = new ArrayList<>();
    public static Vector vehicleTimeStatus = new Vector();
    public static Vector vehicleStatus = new Vector();
}
