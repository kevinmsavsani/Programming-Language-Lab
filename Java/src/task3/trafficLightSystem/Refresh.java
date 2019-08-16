package task3.trafficLightSystem;

import org.javatuples.Triplet;

import javax.swing.table.DefaultTableModel;
import java.util.Date;

public class Refresh extends Thread {
    private static void refreshAction(DefaultTableModel trafficLightDetails, DefaultTableModel outputStatusDetails) {

        trafficLightDetails.setRowCount(0);
        Date date = new Date();
        long time = (date.getTime() / 1000);
        if (Constant.greenTrafficlight==1){
            trafficLightDetails.addRow(new Object[]{"T1", "Green", time - Constant.startTrafficLightTime});
            trafficLightDetails.addRow(new Object[]{"T2", "Red", "--"});
            trafficLightDetails.addRow(new Object[]{"T3", "Red", "--"});
        } else if (Constant.greenTrafficlight==2){
            trafficLightDetails.addRow(new Object[]{"T1", "Red", "--"});
            trafficLightDetails.addRow(new Object[]{"T2", "Green", time - Constant.startTrafficLightTime});
            trafficLightDetails.addRow(new Object[]{"T3", "Red", "--"});
        } else if (Constant.greenTrafficlight==3){
            trafficLightDetails.addRow(new Object[]{"T1", "Red", "--"});
            trafficLightDetails.addRow(new Object[]{"T2", "Red", "--"});
            trafficLightDetails.addRow(new Object[]{"T3", "Green", time - Constant.startTrafficLightTime});
        }

        outputStatusDetails.setRowCount(0);
        for (Triplet<Integer, String, String> user : Constant.userDetails)
        {
            outputStatusDetails.addRow(new Object[]{user.getValue0(),user.getValue1(),user.getValue2(),Constant.vehicleStatus.get(user.getValue0()-1),Constant.vehicleTimeStatus.get(user.getValue0()-1)});
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
