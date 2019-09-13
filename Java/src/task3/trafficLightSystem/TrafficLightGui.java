package task3.trafficLightSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TrafficLightGui extends JFrame {
    Signal green = new Signal(Color.green);
    Signal yellow = new Signal(Color.yellow);
    Signal red = new Signal(Color.red);
    Signal green2 = new Signal(Color.green);
    Signal yellow2 = new Signal(Color.yellow);
    Signal red2 = new Signal(Color.red);
    Signal green3 = new Signal(Color.green);
    Signal yellow3 = new Signal(Color.yellow);
    Signal red3 = new Signal(Color.red);

    public TrafficLightGui(){
        super("Traffic Light");
        getContentPane().setLayout(new FlowLayout());

        green.turnOn(false);
        yellow.turnOn(false);
        red.turnOn(true);
        green2.turnOn(false);
        yellow2.turnOn(false);
        red2.turnOn(true);
        green3.turnOn(false);
        yellow3.turnOn(false);
        red3.turnOn(true);

        JLabel label1 = new JLabel("T1 ", SwingConstants.CENTER);
        Font font = new Font("Courier", Font.BOLD,24);
        label1.setFont(font);
        JLabel label2 = new JLabel("T2 ", SwingConstants.CENTER);
        label2.setFont(font);
        JLabel label3 = new JLabel("T3 ", SwingConstants.CENTER);
        label3.setFont(font);

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                refreshSignal();            }

            private void refreshSignal() {
                Date date = new Date();
                if (Constant.greenTrafficlight == 1){
                    green.turnOn(true);
                    yellow.turnOn(false);
                    red.turnOn(false);
                    green2.turnOn(false);
                    yellow2.turnOn(true);
                    red2.turnOn(false);
                    green3.turnOn(false);
                    yellow3.turnOn(false);
                    red3.turnOn(true);
                    label1.setText("T1 "+ 0 );
                    label2.setText("T2 "+(60-((date.getTime()/1000)-Constant.startTrafficLightTime)));
                    label3.setText("T3 "+(120-((date.getTime()/1000)-Constant.startTrafficLightTime)));
                } else if (Constant.greenTrafficlight == 2){
                    yellow.turnOn(false);
                    green.turnOn(false);
                    red.turnOn(true);
                    green2.turnOn(true);
                    yellow2.turnOn(false);
                    red2.turnOn(false);
                    green3.turnOn(false);
                    yellow3.turnOn(true);
                    red3.turnOn(false);
                    label1.setText("T1 "+(120-((date.getTime()/1000)-Constant.startTrafficLightTime)));
                    label2.setText("T2 "+ 0);
                    label3.setText("T3 "+(60-((date.getTime()/1000)-Constant.startTrafficLightTime)));
                } else if (Constant.greenTrafficlight == 3){
                    red.turnOn(false);
                    yellow.turnOn(true);
                    green.turnOn(false);
                    green2.turnOn(false);
                    yellow2.turnOn(false);
                    red2.turnOn(true);
                    green3.turnOn(true);
                    yellow3.turnOn(false);
                    red3.turnOn(false);
                    label1.setText("T1 "+(60-((date.getTime()/1000)-Constant.startTrafficLightTime)));
                    label2.setText("T2 "+(120-((date.getTime()/1000)-Constant.startTrafficLightTime)));
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
        p1.add(red);
        p1.add(yellow);
        p1.add(green);

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2,BoxLayout.PAGE_AXIS));
        p2.add(label2);
        p2.add(red2);
        p2.add(yellow2);
        p2.add(green2);

        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3,BoxLayout.PAGE_AXIS));
        p3.add(label3);
        p3.add(red3);
        p3.add(yellow3);
        p3.add(green3);

        getContentPane().add(p1);
        getContentPane().add(p2);
        getContentPane().add(p3);
        pack();
    }
}

class Signal extends JPanel {

    Color on;
    int radius = 40;
    int border = 10;
    boolean change;

    Signal(Color color) {
        on = color;
        change = true;
    }

    public void turnOn(boolean a) {
        change = a;
        repaint();
    }

    public Dimension getPreferredSize() {
        int size = (radius + border) * 2;
        return new Dimension(size, size);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 100, 100);

        if (change) {
            g.setColor(on);
        } else {
            g.setColor(on.darker().darker().darker());
        }
        g.fillOval(border, border, 2 * radius, 2 * radius);
    }
}