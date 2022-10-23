import ovalButton.*;

import java.awt.Color;
import java.lang.Math;

import javax.swing.BorderFactory;

public class MyOval extends OvalButton {
    private int tickRate;

    public MyOval(int tickOffset) {
        super(OvalButton.SHAPE_OVAL, OvalButton.HORIZONTAL, Color.GREEN, Color.RED, Color.BLACK, Color.BLACK);
        this.tickRate = (int) (Math.random() * 20);
        this.repaint();
    }

    public void changeColor() {
        try {
            if (this.currentBackground == Color.GREEN) {
                this.setColorNormal(Color.RED);
                this.paint(getGraphics());
            } else {
                this.setColorNormal(Color.GREEN);
                this.paint(getGraphics());
            }
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }

    public int getTickRate() {
        return this.tickRate;
    }

}
