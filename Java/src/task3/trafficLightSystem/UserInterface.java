package task3.trafficLightSystem;

import org.javatuples.Triplet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class UserInterface {
    public static List<String> directions = new ArrayList<>();
    public static List<Triplet<Integer, String, String>> userDetails = new ArrayList<>();

    public static void generateGui() {
        JFrame frame = new JFrame(); //creating instance of JFrame
        JLabel sourceDirection = new JLabel("Source Direction");
        JLabel destinationDirection = new JLabel("Destination Direction");
        JButton submitButton = new JButton("Submit");
        JButton addButton = new JButton("Add More Users");
        JButton clearButton = new JButton("Clear");
        JButton statusButton = new JButton("Status Button");

        directions.add("South");
        directions.add("East");
        directions.add("West");
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
        addButton.setBounds(100, 400, 200, 40);
        clearButton.setBounds(300, 400, 200, 40);
        outputTableScrollPane.setBounds(0, 500, 600, 200);
        submitButton.setBounds(100, 700, 200, 40);
        statusButton.setBounds(300, 700, 200, 40);

        addButton.addActionListener(actionEvent -> {
            String item1 = directions.get(sourceDirectionList.getSelectedIndex());
            String item2 = directions.get(destinationDirectionList.getSelectedIndex());
            userDetails.add(new Triplet<>(userDetails.size()+1,item1,item2 ));
            outputTable.setRowCount(0);

            for (Triplet<Integer, String, String> user : userDetails)
            {
                outputTable.addRow(new Object[]{user.getValue0() ,user.getValue1(), user.getValue2()});
            }
            if (userDetails.size() > 0) {
                submitButton.setEnabled(true);
                clearButton.setEnabled(true);
                statusButton.setEnabled(false);
            } else {
                clearButton.setEnabled(false);
                submitButton.setEnabled(false);
                statusButton.setEnabled(false);
            }
        });

        clearButton.addActionListener(actionEvent -> {
            userDetails.clear();
            outputTable.setRowCount(0);
            submitButton.setEnabled(false);
            statusButton.setEnabled(false);
            clearButton.setEnabled(false);
        });

        submitButton.addActionListener(actionEvent -> {
            String message = "Hii";
            JOptionPane.showMessageDialog(frame, message);
            statusButton.setEnabled(true);
        });

        statusButton.addActionListener(actionEvent -> generateStatusGui());

        clearButton.setEnabled(false);
        submitButton.setEnabled(false);
        statusButton.setEnabled(false);

        frame.add(sourceDirection);
        frame.add(sourceDirectionList);
        frame.add(destinationDirection);
        frame.add(destinationDirectionList);
        frame.add(addButton);
        frame.add(clearButton);
        frame.add(outputTableScrollPane);
        frame.add(submitButton);
        frame.add(statusButton);

        frame.setSize(600, 800);//600 width and 800 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
    }

    public static void generateStatusGui() {
        JFrame frame = new JFrame(); //creating instance of JFrame
        //JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new BorderLayout());
        JPanel p3 = new JPanel(new BorderLayout());

//        JLabel sourceDirection = new JLabel("Source Direction");
//        JLabel destinationDirection = new JLabel("Destination Direction");
//        JButton submitButton = new JButton("Submit");
//        JButton addButton = new JButton("Add More Users");
//        JButton clearButton = new JButton("Clear");
//        directions.add("South");
//        directions.add("East");
//        directions.add("West");
//        JList sourceDirectionList = new JList(directions.toArray());
//        JList destinationDirectionList = new JList(directions.toArray());
//
//
//        DefaultTableModel outputTable = new DefaultTableModel(new String[]{"Vehicle Number", "Status"}, 0);
//        JTable outputJTable = new JTable(outputTable);
//        JScrollPane outputTableScrollPane = new JScrollPane(outputJTable);
//
//        // x axis, y axis, width, height
//        sourceDirection.setBounds(50, 50, 250, 30);
//        sourceDirectionList.setBounds(350, 50, 100, 150);
//        destinationDirection.setBounds(50, 250, 250, 30);
//        destinationDirectionList.setBounds(350, 250, 100, 150);
//        addButton.setBounds(100, 400, 200, 40);
//        clearButton.setBounds(300, 400, 200, 40);
//        outputTableScrollPane.setBounds(0, 500, 600, 200);
//        submitButton.setBounds(100, 700, 200, 40);
//
//        addButton.addActionListener(actionEvent -> {
//            String item1 = directions.get(sourceDirectionList.getSelectedIndex());
//            String item2 = directions.get(destinationDirectionList.getSelectedIndex());
//            userDetails.add(new Triplet<>(userDetails.size()+1,item1,item2 ));
//            outputTable.setRowCount(0);
//            if (userDetails.size() > 0) {
//                submitButton.setEnabled(true);
//                clearButton.setEnabled(true);
//            } else {
//                clearButton.setEnabled(false);
//                submitButton.setEnabled(false);
//            }
//        });
//
//        clearButton.addActionListener(actionEvent -> {
//            userDetails.clear();
//            outputTable.setRowCount(0);
//            submitButton.setEnabled(false);
//            clearButton.setEnabled(false);
//        });
//
//        submitButton.addActionListener(actionEvent -> {
//            String message = "Hii";
//            JOptionPane.showMessageDialog(frame, message);
//        });
//
//        clearButton.setEnabled(false);
//        submitButton.setEnabled(false);
//
//        p1.add(sourceDirection);
//        p1.add(sourceDirectionList);
//        p1.add(destinationDirection);
//        p1.add(destinationDirectionList);
//        p1.add(addButton);
//        p1.add(clearButton);
//        p1.add(outputTableScrollPane);
//        p1.add(submitButton);
//
//        p1.setLayout(null);//using no layout managers

        JButton refresh = new JButton("Refresh"); //creating instance of JButton
        DefaultTableModel trafficLightDetails = new DefaultTableModel(new String[]{"Traffic Light", "Status", "Time"}, 0);
        DefaultTableModel outputStatusTable = new DefaultTableModel(new String[]{"Vehicle", "Source", "Destination","Status", "Remaining Time"}, 0);

        JTable trafficLightTable = new JTable(trafficLightDetails);
        JTable statusTable = new JTable(outputStatusTable);
        JScrollPane trafficLightScrollPane = new JScrollPane(trafficLightTable);
        JScrollPane outputStatusScrollPane = new JScrollPane(statusTable);

        JTabbedPane tp = new JTabbedPane();
        refresh.addActionListener(actionEvent -> refreshAction(trafficLightDetails, outputStatusTable));
        refreshAction( trafficLightDetails, outputStatusTable);

        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        p2.add(trafficLightScrollPane, BorderLayout.CENTER);
        p3.add(outputStatusScrollPane, BorderLayout.CENTER);
        //tp.add("User Input", p1);
        tp.add("Traffic Light Status", p2);
        tp.add("Vehicle Status", p3);
        frame.add(tp, BorderLayout.CENTER);
        frame.add(refresh, BorderLayout.PAGE_END);
        frame.setVisible(true); //making the frame visible
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
        for (Triplet<Integer, String, String> user : userDetails)
        {
            outputStatusDetails.addRow(new Object[]{user.getValue0() ,user.getValue1(), user.getValue2()});
        }
    }
}
