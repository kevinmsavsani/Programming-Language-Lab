package task2.coldDrinkManufacturing;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/************* Class for global variables ***********/
public class Constant {

    public static String fileName = "src/task2/coldDrinkManufacturing/input.txt";

    public static int totalB1Bottles;
    public static int totalB2Bottles;
    public static int observationTime;

    public static int packagingB1TraySize=2;
    public static int packagingB2TraySize=3;
    public static int unfinishedTrayPackagingInput = 1;
    public static int trayPackagingInput = 1;
    public static int unfinishedTraySealingInput = 2;
    public static int packagedB1Bottles=0;
    public static int packagedB2Bottles=0;
    public static int sealedB1Bottles=0;
    public static int sealedB2Bottles=0;

    public static BlockingQueue packagingB1 = new ArrayBlockingQueue(2);
    public static BlockingQueue packagingB2 = new ArrayBlockingQueue(3);
    public static BlockingQueue sealing = new ArrayBlockingQueue(2);
}
