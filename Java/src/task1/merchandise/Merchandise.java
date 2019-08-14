package task1.merchandise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.javatuples.Triplet;

public class Merchandise{

    private List<Setup> Setups; // List of Setup Threads
    private Order order;

    private void startMachine() {
        for (Setup setup : Setups)
        {
            setup.start();
        }

        for (Setup setup : Setups)
        {
            try {
                setup.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Merchandise() {
        Setups = new ArrayList<>();
        for (int i = 0; i < Constant.numberOfOrder; i++) {
            Setup setup = new Setup(this, this.order, i+1);
            Setups.add(setup);
        }
    }

    public static void main(String[]args) throws FileNotFoundException {
        File file = new File(Constant.fileName);
        Scanner scanner = new Scanner(file);

        Constant.numberOfOrder = scanner.nextInt();
        int inputNumber = 0;
        while (scanner.hasNextLine()  && inputNumber++ < Constant.numberOfOrder) {
            int index = scanner.nextInt();
            Character type = scanner.next().charAt(0);
            int count = scanner.nextInt();
            Constant.orderList.add(new Triplet<>(index,type,count));
        }

        Order.printInventory();

        Merchandise merchandise = new Merchandise();

        merchandise.startMachine();
    }
}
