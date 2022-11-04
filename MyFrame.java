/*
* Jason Boyett - jaboye2448
* CIT 4423 01
* October 23, 2022
* mac OS
*/
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ovalButton.OvalButton;

public class MyFrame extends JFrame {

    JPanel panel = new JPanel();
    GridLayout grid = new GridLayout(6, 4, 10,10);//makes all gui elements appear in a grid
    JButton runButton = new OvalButton();
    double tickOffset = 15;//this is the multiplier for the individual buttons tick rate. Do not set below 10
    //program will still run if tickOffset is set below 10 but the buttons will change so fast it will look pretty bad
    //setting tickOffset below 0 will cause the loop thread to reset it to 10 to prevent runtime errors
    JButton colorChangerPrimary = new JButton("primary color");
    JButton colorChangerSecondary = new JButton("secondary color");
    JTextField firstField = new JTextField("Press ...");
    JTextField secondField = new JTextField("To begin.");
    MyOval[] ovals = new MyOval[(grid.getColumns() * grid.getRows())+1];
    LoopThread loop;
    TextThread changeText;

    public MyFrame() {
        panel.setLayout(grid);
        panel.setSize(600, 600);
        //this for loop creates several Oval buttons and adds them to an array
        //it then add them to the panel which places them using a grid layout manager
        

        //this JTextField acts as an empty space to help align the GUI elements in the Grid layout
        this.panel.add(colorChangerPrimary);

        //a text field telling the user to pres the runButton
        firstField.setHorizontalAlignment(JTextField.CENTER);
        firstField.setEditable(false);
        firstField.setBorder(null);
        firstField.setOpaque(false);
        panel.add(firstField);
       
        //this button starts and stops the sim
        runButton.setText("START");
        runButton.addActionListener(e -> pressStartStop());//when pressed this button calls the pressStartStop method
        panel.add(runButton);

        //a text field telling the user what the runButton will do when pressed
        secondField.setHorizontalAlignment(JTextField.CENTER);
        secondField.setEditable(false);
        secondField.setBorder(null);
        secondField.setOpaque(false);
        panel.add(secondField);

        //a spacer at the end of the grid
        panel.add(colorChangerSecondary);

        
        for (int i = 0; i < ovals.length; i++) {
            this.ovals[i] = new MyOval();
            panel.add(this.ovals[i]);
        }
        
        //this block sets up the characteristics of the buttons that change the primary
        //and secondary colors
        colorChangerSecondary.addActionListener(e -> setSecondary());
        colorChangerPrimary.addActionListener(e -> setPrimary());

        this.add(panel);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void pressStartStop() {
        try {

            if (this.runButton.getText().equalsIgnoreCase("start")){//begins the animation and changes the text
            //the button text changes from "start" when the loop is stopped and "stop" when the loop
                this.loop = new LoopThread(ovals, tickOffset);
                this.changeText = new TextThread(this);//since the TextThread takes a MyFrame as an argument passing it the this key word gives it this instance of MyFrame
                this.changeText.setPriority(Thread.MAX_PRIORITY);//The text needs to be changed before anything else happens so that the loop can be stopped
                this.changeText.start();//Changes the text of both the text fields and the button
                //this is done on its own thread so that the loop thread can handle the animation only
                this.loop.start();//now that the loop is set up to be able to be stopped and allow other processes it can begin
            } else if (this.runButton.getText().equals("STOP")) {//ends the animation and resets text
                this.loop.interrupt();//stops the infinite loop of the animation
                this.changeText = new TextThread(this);//creates a thread that will reset the text
                this.changeText.start();//runs the thread to change the text
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void setPrimary(){
        Color color = JColorChooser.showDialog(this, "Primary", ovals[0].getPrimary());
        if(color != null){
            for( MyOval oval: this.ovals){
                oval.setPrimary(color);
                oval.setColorNormal(color);
                this.repaint();
            }
        }
    }

    private void setSecondary(){
        Color color = JColorChooser.showDialog(this, "Primary", ovals[0].getSecondary());
        if(color != null){
            for( MyOval oval: this.ovals){
                oval.setSecondary(color);
                oval.setColorHighlighted(color);
                this.repaint();
            }
        }
    }

}