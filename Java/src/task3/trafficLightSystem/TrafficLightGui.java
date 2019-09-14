package task3.trafficLightSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrafficLightGui{
    SignalLight greenT1 = new SignalLight(Color.green);
    SignalLight redT1 = new SignalLight(Color.red);
    SignalLight greenT2 = new SignalLight(Color.green);
    SignalLight redT2 = new SignalLight(Color.red);
    SignalLight greenT3 = new SignalLight(Color.green);
    SignalLight redT3 = new SignalLight(Color.red);

    public void TrafficLightGui1(JPanel panel) {
        greenT1.turnOn(false);
        redT1.turnOn(true);
        greenT2.turnOn(false);
        redT2.turnOn(true);
        greenT3.turnOn(false);
        redT3.turnOn(true);

        Font font = new Font("Courier", Font.BOLD,24);
        JLabel label1 = new JLabel("T1 ", SwingConstants.CENTER);
        label1.setFont(font);
        JLabel label2 = new JLabel("T2 ", SwingConstants.CENTER);
        label2.setFont(font);
        JLabel label3 = new JLabel("T3 ", SwingConstants.CENTER);
        label3.setFont(font);

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                refreshSignal();
            }

            private void refreshSignal() {
                if (Constant.greenTrafficlight == 1){
                    greenT1.turnOn(true);
                    redT1.turnOn(false);
                    greenT2.turnOn(false);
                    redT2.turnOn(true);
                    greenT3.turnOn(false);
                    redT3.turnOn(true);
                    label1.setText("T1 "+ 0 );
                    label2.setText("T2 "+(60-(Constant.programTime-Constant.startTrafficLightTime)));
                    label3.setText("T3 "+(120-(Constant.programTime-Constant.startTrafficLightTime)));
                } else if (Constant.greenTrafficlight == 2){
                    greenT1.turnOn(false);
                    redT1.turnOn(true);
                    greenT2.turnOn(true);
                    redT2.turnOn(false);
                    greenT3.turnOn(false);
                    redT3.turnOn(true);
                    label1.setText("T1 "+(120-(Constant.programTime-Constant.startTrafficLightTime)));
                    label2.setText("T2 "+ 0);
                    label3.setText("T3 "+(60-(Constant.programTime-Constant.startTrafficLightTime)));
                } else if (Constant.greenTrafficlight == 3){
                    redT1.turnOn(true);
                    greenT1.turnOn(false);
                    greenT2.turnOn(false);
                    redT2.turnOn(true);
                    greenT3.turnOn(true);
                    redT3.turnOn(false);
                    label1.setText("T1 "+(60-(Constant.programTime-Constant.startTrafficLightTime)));
                    label2.setText("T2 "+(120-(Constant.programTime-Constant.startTrafficLightTime)));
                    label3.setText("T3 "+0);
                }
            }
        };
        Timer timer = new Timer(100 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();


        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1,BoxLayout.PAGE_AXIS));
        p1.add(label1);
        p1.add(redT1);
        p1.add(greenT1);

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2,BoxLayout.PAGE_AXIS));
        p2.add(label2);
        p2.add(redT2);
        p2.add(greenT2);

        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3,BoxLayout.PAGE_AXIS));
        p3.add(label3);
        p3.add(redT3);
        p3.add(greenT3);

        JPanel panelOutput = new JPanel();
        GridLayout experimentLayout = new GridLayout(0,3);
        panelOutput.setLayout(experimentLayout);
        panelOutput.add(p1);
        panelOutput.add(p2);
        panelOutput.add(p3);
        panel.add(panelOutput,BorderLayout.CENTER);
    }
}