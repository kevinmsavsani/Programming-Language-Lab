package task3.trafficLightSystem;

import javax.swing.*;
import java.awt.*;

class SignalLight extends JPanel {

    Color colorOn;
    int boundary = 10;
    boolean lightChange;
    int trafficLightRadius = 40;


    SignalLight(Color color) {
        colorOn = color;
        lightChange = true;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 100, 100);

        if (lightChange) {
            g.setColor(colorOn);
        } else {
            g.setColor(colorOn.darker().darker().darker());
        }
        g.fillOval(boundary, boundary, 2 * trafficLightRadius, 2 * trafficLightRadius);
    }

    public Dimension getPreferredSize() {
        int size = (trafficLightRadius + boundary) * 2;
        return new Dimension(size, size);
    }

    public void turnOn(boolean a) {
        lightChange = a;
        repaint();
    }
}
