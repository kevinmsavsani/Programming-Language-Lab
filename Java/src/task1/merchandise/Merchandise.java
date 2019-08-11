package task1.merchandise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import org.javatuples.Triplet;

public class Merchandise{

    private List<Setup> Setups; // List of Setup Threads
    private Order order = new Order();
    private List<Semaphore> SemLocks;   // Semaphore locks


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
        createSemLocks();

        Setups = new ArrayList<>();
        for (int i = 0; i < Constant.numberOfOrder; i++) {
            Setup setup = new Setup(this, this.order, i+1);
            Setups.add(setup);
        }
    }

    int pickOrder(){
        synchronized (Constant.orderNumber) {
            Constant.orderNumber++;
            if (Constant.orderNumber < Constant.numberOfOrder) {
                if(Constant.orderList.get(Constant.orderNumber).getValue1() == Constant.small) {
                    synchronized (Constant.small) {
                        boolean success = SemLocks.get(0).tryAcquire();

                        if (success) {

                        } else {
                            Constant.orderNumber--;
                            return pickOrder();
                        }
                    }
                }
                else if(Constant.orderList.get(Constant.orderNumber).getValue1() == Constant.medium) {
                    synchronized (Constant.medium) {
                        boolean success = SemLocks.get(1).tryAcquire();

                        if (success) {

                        } else {
                            Constant.orderNumber--;
                            return pickOrder();
                        }
                    }
                }
                else if(Constant.orderList.get(Constant.orderNumber).getValue1() == Constant.large) {
                    synchronized (Constant.large) {
                        boolean success = SemLocks.get(2).tryAcquire();

                        if (success) {

                        } else {
                            Constant.orderNumber--;
                            return pickOrder();
                        }
                    }
                }
                else if(Constant.orderList.get(Constant.orderNumber).getValue1() == Constant.cap) {
                    synchronized (Constant.cap) {
                        boolean success = SemLocks.get(3).tryAcquire();

                        if (success) {

                        } else {
                            Constant.orderNumber--;
                            return pickOrder();
                        }
                    }
                }
                return Constant.orderNumber;
            } else {
                return -1;
            }
        }
    }

    private void createSemLocks() {
        SemLocks = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Semaphore SemLock = new Semaphore(1);
            SemLocks.add(SemLock);
        }
        SemLocks.get(0).release();
        SemLocks.get(1).release();
        SemLocks.get(2).release();
        SemLocks.get(3).release();
    }

    public static void main(String[]args) throws FileNotFoundException {
        File file = new File(Constant.fileName);
        Scanner scanner = new Scanner(file);

        Constant.numberOfOrder = scanner.nextInt();
        while (scanner.hasNextLine()) {
            int index = scanner.nextInt();
            Character type = scanner.next().charAt(0);
            int count = scanner.nextInt();
            Constant.orderList.add(new Triplet<>(index,type,count));
        }

        Order.printInventory();

        Merchandise merchandise = new Merchandise();

        merchandise.startMachine();
    }

    void releaseLock(int orderNumber) {
        if(Constant.orderList.get(orderNumber).getValue1() == Constant.small) {
            synchronized (Constant.small) {
                System.out.println(Constant.orderList.get(orderNumber).getValue0()+ "synchronized" + Constant.orderList.get(orderNumber).getValue1());
                SemLocks.get(0).release();
            }
        }
        else if(Constant.orderList.get(orderNumber).getValue1() == Constant.medium) {
            synchronized (Constant.medium) {
                System.out.println(Constant.orderList.get(orderNumber).getValue0()+ "synchronized" + Constant.orderList.get(orderNumber).getValue1());
                SemLocks.get(1).release();
            }
        }
        else if(Constant.orderList.get(orderNumber).getValue1() == Constant.large) {
            synchronized (Constant.large) {
                System.out.println(Constant.orderList.get(orderNumber).getValue0()+ "synchronized" + Constant.orderList.get(orderNumber).getValue1());
                SemLocks.get(2).release();
            }
        }
        else if(Constant.orderList.get(orderNumber).getValue1() == Constant.cap) {
            synchronized (Constant.cap) {
                System.out.println(Constant.orderList.get(orderNumber).getValue0()+ "synchronized" + Constant.orderList.get(orderNumber).getValue1());
                SemLocks.get(3).release();
            }
        }
    }

}
