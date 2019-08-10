package task1.merchandise;

import java.util.ArrayList;
import java.util.List;
import org.javatuples.Pair;
import org.javatuples.Triplet;

public class Constant {
    public static String fileName = "src/task1/merchandise/input.txt";
    //public static List<String> order = new ArrayList<>();
    public static List<Triplet<Integer, Character, Integer>> orderN = new ArrayList<>();

    public static int numberOfOrder;
    public static int totalSmall = 4;
    public static int totalMedium = 5;
    public static int totalLarge = 2;
    public static int totalCap = 4;
}
