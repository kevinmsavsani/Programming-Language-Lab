package task1.merchandise;

public class Order {

    public static void printInventory(){
        System.out.println("Inventory --");
        System.out.println("S   |   M   |   L   |   C");
        System.out.println(String.format("%d   |   %d   |   %d   |   %d \n",Constant.totalSmall,Constant.totalMedium,Constant.totalLarge,Constant.totalCap));
    }

    public static int pickOrder(){
        Constant.orderNumber++;
        if (Constant.orderNumber < Constant.numberOfOrder) {
            return Constant.orderNumber;
        } else {
            return -1;
        }
    }

    public static void orderProcess(Integer orderNumber){

        if(Constant.orderList.get(orderNumber).getValue1() == Constant.small) {
            synchronized (Constant.small) {
                if (Constant.orderList.get(orderNumber).getValue2() <= Constant.totalSmall) {
                    Constant.totalSmall -= Constant.orderList.get(orderNumber).getValue2();
                    synchronized(Constant.print) {
                        System.out.println(String.format("Order %d is successful", Constant.orderList.get(orderNumber).getValue0()));
                        printInventory();
                    }
                } else {
                    synchronized(Constant.print) {
                        System.out.println(String.format("Order %d failed", Constant.orderList.get(orderNumber).getValue0()));
                        printInventory();
                    }
                }
            }
        }
        else if(Constant.orderList.get(orderNumber).getValue1() == Constant.medium) {
            synchronized (Constant.medium) {
                if (Constant.orderList.get(orderNumber).getValue2() <= Constant.totalMedium) {
                    Constant.totalMedium -= Constant.orderList.get(orderNumber).getValue2();
                    synchronized(Constant.print) {
                        System.out.println(String.format("Order %d is successful", Constant.orderList.get(orderNumber).getValue0()));
                        printInventory();
                    }
                } else {
                    synchronized(Constant.print){
                        System.out.println(String.format("Order %d failed", Constant.orderList.get(orderNumber).getValue0()));
                        printInventory();
                    }
                }
            }
        }
        else if(Constant.orderList.get(orderNumber).getValue1() == Constant.large) {
            synchronized (Constant.large) {
                if (Constant.orderList.get(orderNumber).getValue2() <= Constant.totalLarge) {
                    Constant.totalLarge -= Constant.orderList.get(orderNumber).getValue2();
                    synchronized(Constant.print) {
                        System.out.println(String.format("Order %d is successful", Constant.orderList.get(orderNumber).getValue0()));
                        printInventory();
                    }
                } else {
                    synchronized(Constant.print) {
                        System.out.println(String.format("Order %d failed", Constant.orderList.get(orderNumber).getValue0()));
                        printInventory();
                    }
                }
            }
        }
        else if(Constant.orderList.get(orderNumber).getValue1() == Constant.cap) {
            synchronized (Constant.cap) {
                if (Constant.orderList.get(orderNumber).getValue2() <= Constant.totalCap) {
                    Constant.totalCap -= Constant.orderList.get(orderNumber).getValue2();
                    synchronized(Constant.print) {
                        System.out.println(String.format("Order %d is successful", Constant.orderList.get(orderNumber).getValue0()));
                        printInventory();
                    }
                } else {
                    synchronized(Constant.print) {
                        System.out.println(String.format("Order %d failed", Constant.orderList.get(orderNumber).getValue0()));
                        printInventory();
                    }
                }
            }
        }
    }

}
