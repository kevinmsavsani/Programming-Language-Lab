package task1.merchandise;

public class Setup extends Thread {
    private Merchandise merchandise;
    private Order order;

    Setup(Merchandise merchandise, Order order, int name){
        setName(String.valueOf(name));
        this.merchandise = merchandise;
        this.order = order;
    }

    @Override
    public void run(){
//      while(true) {
        int orderNumber = order.pickOrder();

//      if (orderNumber < 0) {
        // Stop the thread
        //System.out.println("Thread " + getName() + " Stopped!");
        //stop();
//      }
        System.out.println("Order Number " + Constant.orderList.get(orderNumber).getValue0() + " of type " + Constant.orderList.get(orderNumber).getValue1() + " recieved by Thread - " + getName());
        System.out.println("");
        order.orderProcess(orderNumber);
        // Stop the thread
        System.out.println("Thread " + getName() + " Stopped!");
        stop();
//      }
    }
}
