/*
* Jason Boyett - jaboye2448
* CIT 4423 01
* October 23, 2022
* mac OS
*/
//import ovalButton.*;//a repo from github that creates oval shaped JButtons
import java.awt.Color;

public class MyOval extends OvalButton {
    private int tickRate;
    private Color primary;
    private Color secondary;

    public MyOval() {
        super(OvalButton.SHAPE_OVAL, OvalButton.HORIZONTAL, Color.green, Color.RED, Color.BLACK, Color.BLACK);
        this.tickRate = (int) (Math.random() * 20);//each MyOval object has a unique tick rate that is randomly generated
        this.primary = Color.GREEN;
        this.secondary = Color.RED;
    }

    public void changeColor() {//when this method is called the button will change color from green to red of vise versa
        try {
            if (this.currentBackground == this.primary) {//if the button is green it gets changed to red
                this.setColorNormal(this.secondary);//sets the color default color of the button to red
                this.setColorHighlighted(this.secondary);//changes the color of the button when the mouse is over it
                this.paint(getGraphics());//immediately paints the button with the appropriate color
                //standard practice is to call repaint() but this schedules the component to be painted
                //in this case the component must be painted immediately
            } else {//since the color of the button is a binary between red and green if it's not green then it must be red
                this.setColorNormal(this.primary);//sets the default color to green
                this.setColorHighlighted(this.secondary);//sets the mouse over color to red
                this.paint(getGraphics());//immediately paints the button with the appropriate color
            }
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }

    public int getTickRate() {//getter for the individual button's tick rate
        return this.tickRate;
    }

    public Color getColor(){
        return this.currentBackground;
    }

    public Color getPrimary(){
        return this.primary;
    }

    public Color getSecondary(){
        return this.secondary;
    }

    public void setPrimary(Color primary) {
        this.primary = primary;
    }

    public void setSecondary(Color secondary) {
        this.secondary = secondary;
    }

}