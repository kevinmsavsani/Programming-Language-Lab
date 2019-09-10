package task2.coldDrinkManufacturing;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Manufacturer {
    Packaging packaging = new Packaging();
    Sealing sealing = new Sealing();
    PackagingThread packagingThread = new PackagingThread(packaging,"Packaging");
    SealingThread sealingThread = new SealingThread(sealing,"Sealing");

    private void printOutput() {
        System.out.println("Bottle Type      Status      Count");
        System.out.println("    B1          Packaged       "+Constant.packagedB1Bottles);
        System.out.println("    B1          Sealed         "+Constant.sealedB1Bottles);
        System.out.println("    B1          In Godown      "+ GodownCounter.getB1Value());
        System.out.println("    B2          Packaged       "+Constant.packagedB2Bottles);
        System.out.println("    B2          Sealed         "+Constant.sealedB2Bottles);
        System.out.println("    B2          In Godown      "+ GodownCounter.getB2Value());

    }

    private void startMachine() {

        packagingThread.start();
        sealingThread.start();

        try {
            packagingThread.join();
            sealingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[]args) throws FileNotFoundException {
        File file = new File(Constant.fileName);
        Scanner scanner = new Scanner(file);
        String line;
        String[] lineVector;

        line = scanner.nextLine();

        //separate all values by comma
        lineVector = line.split(", ");

        //parsing the values to Integer
        Constant.totalB1Bottles=Integer.parseInt(lineVector[0]);
        Constant.totalB2Bottles=Integer.parseInt(lineVector[1]);
        Constant.observationTime=Integer.parseInt(lineVector[2]);

        for (int i = 0; i < Constant.totalB1Bottles; i++) {
            Constant.unfinishedTrayB1.add(1);
        }

        for (int i = 0; i < Constant.totalB2Bottles; i++) {
            Constant.unfinishedTrayB2.add(2);
        }

        Manufacturer manufacturer = new Manufacturer();

        manufacturer.startMachine();

        manufacturer.printOutput();
    }
}
