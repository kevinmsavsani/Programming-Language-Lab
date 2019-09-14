package task3.trafficLightSystem;

import org.javatuples.Quartet;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static task3.trafficLightSystem.Constant.directions;

public class UserInterface extends Thread {

    @Override
    public void run() {
        generateGui();
    }

    public static void generateGui() {
        JFrame frame = new JFrame(); //creating instance of JFrame

        frame.setTitle("Programming Lab Assignment 1");
        JLabel sourceDirection = new JLabel("Source Direction");
        JLabel destinationDirection = new JLabel("Destination Direction");
        JLabel carArrivalTime = new JLabel("Car Arrival Time");
        JLabel numCar = new JLabel("No. Car Arriving");
        JButton addButton = new JButton("Add More Cars");
        JButton statusButton = new JButton("Status Button");

        SpinnerModel spinnerNumberModel = new SpinnerNumberModel(1, 1, 10000, 1);
        SpinnerModel spinnerNumberModelCars = new SpinnerNumberModel(1, 1, 100, 1);
        JSpinner carTime = new JSpinner(spinnerNumberModel);
        JSpinner carNum = new JSpinner(spinnerNumberModelCars);

        directions.add("South");
        directions.add("West");
        directions.add("East");
        JList sourceDirectionList = new JList(directions.toArray());
        JList destinationDirectionList = new JList(directions.toArray());


        DefaultTableModel outputTable = new DefaultTableModel(new String[]{"Vehicle Number", "Status"}, 0);
        JTable outputJTable = new JTable(outputTable);
        JScrollPane outputTableScrollPane = new JScrollPane(outputJTable);

        DefaultTableModel TimeTable = new DefaultTableModel(new String[]{"Time", "Status"}, 0);
        JTable timeJTable = new JTable(TimeTable);
        JScrollPane timeTableScrollPane = new JScrollPane(timeJTable);

        // x axis, y axis, width, height
        timeTableScrollPane.setBounds(80,20,400,40);
        sourceDirection.setBounds(50, 90, 250, 30);
        sourceDirectionList.setBounds(350, 90, 100, 130);
        destinationDirection.setBounds(50, 240, 250, 30);
        destinationDirectionList.setBounds(350, 240, 100, 130);
        carArrivalTime.setBounds(50, 400, 250, 30);
        carTime.setBounds(350, 400, 100, 30);
        numCar.setBounds(50, 460, 250, 30);
        carNum.setBounds(350, 460, 100, 30);
        addButton.setBounds(200, 530, 200, 40);
        outputTableScrollPane.setBounds(0, 600, 600, 140);
        statusButton.setBounds(200, 770, 200, 40);

        addButton.addActionListener(actionEvent -> {
            String item1 = directions.get(sourceDirectionList.getSelectedIndex());
            String item2 = directions.get(destinationDirectionList.getSelectedIndex());
            Integer arrivalTime = (Integer) carTime.getValue();
            Integer numCarArriving = (Integer) carNum.getValue();
            if (arrivalTime > (Constant.programTime - Constant.startTime)) {
                for (int i = 0; i < numCarArriving; i++) {
                    Constant.allUser.add(new Quartet<>(++Constant.vehicleNumber,item1,item2,arrivalTime));
                }
            }
        });

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                outputTable.setRowCount(0);
                for (Quartet<Integer, String, String, Integer> user : Constant.userDetails)
                {
                    if ( user.getValue3() < (Constant.programTime - Constant.startTime)) {
                        outputTable.addRow(new Object[]{user.getValue0(), Constant.vehicleStatus.get(user.getValue0() - 1)});
                    }
                }
                TimeTable.setRowCount(0);
                TimeTable.addRow(new Object[]{"Current Time" , (Constant.programTime - Constant.startTime)});

                if (Constant.userDetails.size() > 0  && !statusButton.isEnabled()) {
                    statusButton.setEnabled(true);
                }
            }
        };
        Timer timer = new Timer(1000 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();

        statusButton.addActionListener(actionEvent -> generateStatusGui());

        statusButton.setEnabled(true);
        frame.add(timeTableScrollPane);
        frame.add(sourceDirection);
        frame.add(sourceDirectionList);
        frame.add(destinationDirection);
        frame.add(destinationDirectionList);
        frame.add(carArrivalTime);
        frame.add(carTime);
        frame.add(numCar);
        frame.add(carNum);
        frame.add(addButton);
        frame.add(outputTableScrollPane);
        frame.add(statusButton);

        frame.setSize(600, 900);//600 width and 1000 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
    }

    public static void generateStatusGui() {
        JFrame frame = new JFrame(); //creating instance of JFrame
        frame.setTitle("Traffic Light System Status");

        JPanel panel = new JPanel();
        TrafficLightGui trafficLightGui = new TrafficLightGui();
        trafficLightGui.TrafficLightGui1(panel);
        JPanel p2 = new JPanel(new BorderLayout());
        JPanel p3 = new JPanel(new BorderLayout());

        DefaultTableModel trafficLightDetails = new DefaultTableModel(new String[]{"Traffic Light", "Status", "Time"}, 0);
        DefaultTableModel outputStatusTable = new DefaultTableModel(new String[]{"Vehicle", "Source", "Destination","Status", "Remaining Time"}, 0);
        JTable trafficLightTable = new JTable(trafficLightDetails);
        JTable statusTable = new JTable(outputStatusTable);
        JScrollPane trafficLightScrollPane = new JScrollPane(trafficLightTable);
        JScrollPane outputStatusScrollPane = new JScrollPane(statusTable);

        JTabbedPane tp = new JTabbedPane();

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                refreshAction( trafficLightDetails, outputStatusTable);            }
        };
        Timer timer = new Timer(100 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();
        frame.setLayout(new BorderLayout());
        frame.setSize(1000,900);
        p2.add(trafficLightScrollPane, BorderLayout.CENTER);
        p3.add(outputStatusScrollPane, BorderLayout.CENTER);
        tp.add("Traffic Light Status", p2);
        tp.add("Vehicle Status", p3);
        frame.add(panel,BorderLayout.PAGE_START);
        frame.add(tp, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    private static void refreshAction(DefaultTableModel trafficLightDetails, DefaultTableModel outputStatusDetails) {

        trafficLightDetails.setRowCount(0);
        if (Constant.greenTrafficlight==1){
            trafficLightDetails.addRow(new Object[]{"T1", "Green", Constant.programTime - Constant.startTrafficLightTime});
            trafficLightDetails.addRow(new Object[]{"T2", "Red", "--"});
            trafficLightDetails.addRow(new Object[]{"T3", "Red", "--"});
        } else if (Constant.greenTrafficlight==2){
            trafficLightDetails.addRow(new Object[]{"T1", "Red", "--"});
            trafficLightDetails.addRow(new Object[]{"T2", "Green", Constant.programTime - Constant.startTrafficLightTime});
            trafficLightDetails.addRow(new Object[]{"T3", "Red", "--"});
        } else if (Constant.greenTrafficlight==3){
            trafficLightDetails.addRow(new Object[]{"T1", "Red", "--"});
            trafficLightDetails.addRow(new Object[]{"T2", "Red", "--"});
            trafficLightDetails.addRow(new Object[]{"T3", "Green", Constant.programTime - Constant.startTrafficLightTime});
        }

        outputStatusDetails.setRowCount(0);
        for (Quartet<Integer, String, String,Integer> user : Constant.userDetails)
        {
            if ( user.getValue3() < (Constant.programTime - Constant.startTime)) {
                outputStatusDetails.addRow(new Object[]{user.getValue0(), user.getValue1(), user.getValue2(), Constant.vehicleStatus.get(user.getValue0() - 1), Constant.vehicleTimeStatus.get(user.getValue0() - 1)});
            }
        }
    }
}
