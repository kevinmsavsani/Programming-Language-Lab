package task2.coldDrinkManufacturing;

import java.util.LinkedList;
import java.util.Queue;

public class Constant {

    public static String fileName = "src/task2/coldDrinkManufacturing/input.txt";

    public static int totalB1Bottles;
    public static int totalB2Bottles;
    public static int observationTime;
    public static int packagingTimeTaken=0;
    public static int sealingTimeTaken=0;

    public static int packagingB1TraySize=2;
    public static int packagingB2TraySize=3;
    public static int unfinishedTrayPackagingInput;
    public static int trayPackagingInput;
    public static int unfinishedTraySealingInput;
    public static int packagedB1Bottles=0;
    public static int packagedB2Bottles=0;
    public static int sealedB1Bottles=0;
    public static int sealedB2Bottles=0;

    public static Queue<Integer> unfinishedTrayB1 = new LinkedList<>();
    public static Queue<Integer> unfinishedTrayB2 = new LinkedList<>();
    public static Queue<Integer> packagingB1 = new LinkedList<>();
    public static Queue<Integer> packagingB2 = new LinkedList<>();
    public static Queue<Integer> sealing = new LinkedList<>();
    public static Queue<Integer> godownB1 = new LinkedList<>();
    public static Queue<Integer> godownB2 = new LinkedList<>();

}
