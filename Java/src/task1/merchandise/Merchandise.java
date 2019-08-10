package task1.merchandise;

import org.javatuples.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.javatuples.Pair;
import org.javatuples.Triplet;

public class Merchandise
{
    public static void main(String[]args) throws FileNotFoundException {
        File file = new File(Constant.fileName);
        Scanner scanner = new Scanner(file);

        Constant.numberOfOrder = scanner.nextInt();
        while (scanner.hasNextLine()) {
            //Constant.order.add(scanner.nextLine());
            int index = scanner.nextInt();
            Character type = scanner.next().charAt(0);
            int count = scanner.nextInt();
            Constant.orderN.add(new Triplet<>(index,type,count));
        }

        order.printInventory();

        order.orderProcess();
    }
}
