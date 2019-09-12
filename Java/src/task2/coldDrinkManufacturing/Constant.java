package task2.coldDrinkManufacturing;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

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

    public static BlockingQueue packagingB1 = new ArrayBlockingQueue(2);
    public static BlockingQueue packagingB2 = new ArrayBlockingQueue(3);
    public static BlockingQueue sealing = new ArrayBlockingQueue(2);
    public static BlockingQueue godownB1 = new ArrayBlockingQueue(1024);
    public static BlockingQueue godownB2 = new ArrayBlockingQueue(1024);
    public static BlockingQueue unfinishedTrayB1 = new ArrayBlockingQueue(1024);
    public static BlockingQueue unfinishedTrayB2 = new ArrayBlockingQueue(1024);

}
