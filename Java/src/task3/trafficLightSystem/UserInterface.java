package task3.trafficLightSystem;

import org.javatuples.Quintet;
import org.javatuples.Triplet;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import static task3.trafficLightSystem.Constant.directions;

public class UserInterface {

    public static void generateGui() {
        JFrame frame = new JFrame(); //creating instance of JFrame
        JLabel sourceDirection = new JLabel("Source Direction");
        JLabel destinationDirection = new JLabel("Destination Direction");
        JButton addButton = new JButton("Add More Cars");
        JButton statusButton = new JButton("Status Button");

        directions.add("South");
        directions.add("West");
        directions.add("East");
        JList sourceDirectionList = new JList(directions.toArray());
        JList destinationDirectionList = new JList(directions.toArray());


        DefaultTableModel outputTable = new DefaultTableModel(new String[]{"Vehicle Number", "Status"}, 0);
        JTable outputJTable = new JTable(outputTable);
        JScrollPane outputTableScrollPane = new JScrollPane(outputJTable);

        // x axis, y axis, width, height
        sourceDirection.setBounds(50, 50, 250, 30);
        sourceDirectionList.setBounds(350, 50, 100, 150);
        destinationDirection.setBounds(50, 250, 250, 30);
        destinationDirectionList.setBounds(350, 250, 100, 150);
        addButton.setBounds(200, 430, 200, 40);
        outputTableScrollPane.setBounds(0, 500, 600, 200);
        statusButton.setBounds(200, 700, 200, 40);

        addButton.addActionListener(actionEvent -> {
            String item1 = directions.get(sourceDirectionList.getSelectedIndex());
            String item2 = directions.get(destinationDirectionList.getSelectedIndex());

            Constant.userDetails.add(new Triplet<>(Constant.userDetails.size()+1,item1,item2));
            Constant.vehicleStatus.add("Pass");
            Constant.vehicleTimeStatus.add("--");
            User userInfo = new User(Constant.userDetails.size() , directionWaitTime(item1,item2));
            userInfo.start();
            outputTable.setRowCount(0);
            for (Triplet<Integer, String, String> user : Constant.userDetails)
            {
                outputTable.addRow(new Object[]{user.getValue0() , Constant.vehicleStatus.get(user.getValue0()-1)});
            }
            if (Constant.userDetails.size() > 0) {
                statusButton.setEnabled(true);
            } else {
                statusButton.setEnabled(false);
            }
        });

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                outputTable.setRowCount(0);
                for (Triplet<Integer, String, String> user : Constant.userDetails)
                {
                    outputTable.addRow(new Object[]{user.getValue0() , Constant.vehicleStatus.get(user.getValue0()-1)});
                }
            }
        };
        Timer timer = new Timer(1000 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();

        statusButton.addActionListener(actionEvent -> generateStatusGui());

        statusButton.setEnabled(false);

        frame.add(sourceDirection);
        frame.add(sourceDirectionList);
        frame.add(destinationDirection);
        frame.add(destinationDirectionList);
        frame.add(addButton);
        frame.add(outputTableScrollPane);
        frame.add(statusButton);

        frame.setSize(600, 800);//600 width and 800 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
    }

    private static int directionWaitTime(String item1, String item2) {
        if(item1.equalsIgnoreCase(Constant.southDirection) && item2.equalsIgnoreCase(Constant.eastDirection)){
            return Constant.southEastWait++;
        }
        else if(item1.equalsIgnoreCase(Constant.westDirection) && item2.equalsIgnoreCase(Constant.southDirection)){
            return Constant.westSouthWait++;
        }
        else if(item1.equalsIgnoreCase(Constant.eastDirection) && item2.equalsIgnoreCase(Constant.westDirection)){
            return Constant.eastWestWait++;
        }
        else {
            return 0;
        }
    }

    public static void generateStatusGui() {
        JFrame frame = new JFrame(); //creating instance of JFrame
        JPanel p2 = new JPanel(new BorderLayout());
        JPanel p3 = new JPanel(new BorderLayout());

        DefaultTableModel trafficLightDetails = new DefaultTableModel(new String[]{"Traffic Light", "Status", "Time"}, 0);
        DefaultTableModel outputStatusTable = new DefaultTableModel(new String[]{"Vehicle", "Source", "Destination","Status", "Remaining Time"}, 0);

        JTable trafficLightTable = new JTable(trafficLightDetails);
        JTable statusTable = new JTable(outputStatusTable);
        JScrollPane trafficLightScrollPane = new JScrollPane(trafficLightTable);
        JScrollPane outputStatusScrollPane = new JScrollPane(statusTable);

        JTabbedPane tp = new JTabbedPane();
        refreshAction( trafficLightDetails, outputStatusTable);

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                refreshAction( trafficLightDetails, outputStatusTable);            }
        };
        Timer timer = new Timer(1000 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        p2.add(trafficLightScrollPane, BorderLayout.CENTER);
        p3.add(outputStatusScrollPane, BorderLayout.CENTER);
        tp.add("Traffic Light Status", p2);
        tp.add("Vehicle Status", p3);
        frame.add(tp, BorderLayout.CENTER);
        frame.setVisible(true);
    }


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
}
