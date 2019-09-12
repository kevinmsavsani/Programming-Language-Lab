package task3.trafficLightSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                refreshSignal();            }

            private void refreshSignal() {
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
                }
            }
        };
        Timer timer = new Timer(100 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();

        JLabel lable1 = new JLabel("T1", SwingConstants.CENTER);
        Font font = new Font("Courier", Font.BOLD,24);
        lable1.setFont(font);
        JLabel lable2 = new JLabel("T2", SwingConstants.CENTER);
        lable2.setFont(font);
        JLabel lable3 = new JLabel("T3", SwingConstants.CENTER);
        lable3.setFont(font);


        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1,BoxLayout.PAGE_AXIS));
        p1.add(lable1);
        p1.add(red);
        p1.add(yellow);
        p1.add(green);

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2,BoxLayout.PAGE_AXIS));
        p2.add(lable2);
        p2.add(red2);
        p2.add(yellow2);
        p2.add(green2);

        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3,BoxLayout.PAGE_AXIS));
        p3.add(lable3);
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