import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ovalButton.OvalButton;

public class MyFrame extends JFrame {

    JPanel panel = new JPanel();
    GridLayout grid = new GridLayout(5, 4);
    JButton runButton = new OvalButton();
    int tickOffset = 10;
    JTextField spacer = new JTextField();
    JTextField spacer2 = new JTextField();
    JTextField firstField = new JTextField("Press ...");
    JTextField secondField = new JTextField("To begin.");
    MyOval ovals[] = new MyOval[grid.getColumns() * grid.getRows()];
    LoopThread loop;
    TextThread changeText;

    public MyFrame() {
        panel.setLayout(grid);
        panel.setSize(600, 600);
        //this for loop creates several Oval buttons and adds them to an array
        //it then add them to the panel which places them using a grid layout manager
        for (int i = 0; i < ovals.length; i++) {
            this.ovals[i] = new MyOval(tickOffset);
            panel.add(this.ovals[i]);
        }

        spacer.setEditable(false);
        spacer.setBorder(null);
        spacer.setOpaque(false);
        this.panel.add(spacer);

        firstField.setHorizontalAlignment(JTextField.CENTER);
        firstField.setEditable(false);
        firstField.setBorder(null);
        firstField.setOpaque(false);
        panel.add(firstField);
        
        runButton.setText("START");//this button starts and stops the sim
        runButton.addActionListener(e -> pressStartStop());
        panel.add(runButton);

        secondField.setHorizontalAlignment(JTextField.CENTER);
        secondField.setEditable(false);
        secondField.setBorder(null);
        secondField.setOpaque(false);
        panel.add(secondField);
        spacer.setHorizontalAlignment(JTextField.CENTER);
        spacer2.setHorizontalAlignment(JTextField.CENTER);
        spacer2.setEditable(false);
        spacer2.setOpaque(false);
        spacer2.setBorder(null);
        panel.add(spacer2);
        this.add(panel);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void pressStartStop() {
        try {
            if (this.runButton.getText() == "START") {
                this.loop = new LoopThread(ovals, tickOffset);
                this.changeText = new TextThread(firstField, secondField, runButton);
                this.changeText.setPriority(Thread.MAX_PRIORITY);
                this.changeText.start();
                this.loop.setDaemon(true);
                this.loop.setPriority(Thread.MIN_PRIORITY);
                this.loop.start();
                this.loop.notifyAll();
            } else if (this.runButton.getText().equals("STOP")) {
                this.loop.interrupt();
                this.changeText = new TextThread(firstField, secondField, runButton);
                this.changeText.start();
                this.changeText.join();
                this.loop.join();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}