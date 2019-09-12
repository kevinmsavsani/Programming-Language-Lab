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
        int orderNumber = order.pickOrder(Integer.parseInt(getName()));

        order.orderProcess(orderNumber);
        // Stop the thread
        stop();
    }
}
