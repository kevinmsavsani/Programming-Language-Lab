package task1.merchandise;

public class order {

    public static void printInventory(){
        System.out.println("Inventory --");
        System.out.println("S   |   M   |   L   |   C");
        System.out.println(String.format("%d   |   %d   |   %d   |   %d",Constant.totalSmall,Constant.totalMedium,Constant.totalLarge,Constant.totalCap));
    }

    public static void orderProcess() {
        int orderNumber;

        for (orderNumber=0;orderNumber<Constant.numberOfOrder;orderNumber++)
        {
            if(Constant.orderN.get(orderNumber).getValue1() == 'S')
            {
                if (Constant.orderN.get(orderNumber).getValue2() <= Constant.totalSmall)
                {
                    System.out.println(String.format("Order %d is successful",orderNumber));
                    Constant.totalSmall -= Constant.orderN.get(orderNumber).getValue2();
                }
                else {
                    System.out.println(String.format("Order %d failed",orderNumber));
                }
            }
            else if(Constant.orderN.get(orderNumber).getValue1() == 'M')
            {
                if (Constant.orderN.get(orderNumber).getValue2() <= Constant.totalMedium)
                {
                    System.out.println(String.format("Order %d is successful",orderNumber));
                    Constant.totalMedium -= Constant.orderN.get(orderNumber).getValue2();
                }
                else {
                    System.out.println(String.format("Order %d failed",orderNumber));
                }
            }
            else if(Constant.orderN.get(orderNumber).getValue1() == 'L')
            {
                if (Constant.orderN.get(orderNumber).getValue2() <= Constant.totalLarge)
                {
                    System.out.println(String.format("Order %d is successful",orderNumber));
                    Constant.totalLarge -= Constant.orderN.get(orderNumber).getValue2();
                }
                else {
                    System.out.println(String.format("Order %d failed",orderNumber));
                }
            }
            else if(Constant.orderN.get(orderNumber).getValue1() == 'C')
            {
                if (Constant.orderN.get(orderNumber).getValue2() <= Constant.totalCap)
                {
                    System.out.println(String.format("Order %d is successful",orderNumber));
                    Constant.totalCap -= Constant.orderN.get(orderNumber).getValue2();
                }
                else {
                    System.out.println(String.format("Order %d failed",orderNumber));
                }
            }

            printInventory();
        }
    }

}
