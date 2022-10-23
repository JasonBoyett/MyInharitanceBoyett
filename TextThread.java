/*
* Jason Boyett - jaboye2448
* CIT 4423 01
* October 23, 2022
* mac OS
*/
import javax.swing.JButton;
import javax.swing.JTextField;

public class TextThread extends Thread {
    JTextField textField;
    JButton runButton;

    public TextThread(MyFrame frame) {//constructor for thread
        this.textField = frame.secondField;
        this.runButton = frame.runButton;
    }

    @Override
    public void run() {
        if (this.runButton.getText().equalsIgnoreCase("start")) {//the text of the run button is "start" while the loop i not running
        //if this condition is triggered the GUI text is set to the appropriate state
            this.runButton.setText("STOP");
            this.textField.setText("to pause");
        } else if (this.runButton.getText().equalsIgnoreCase("stop")) {//the text of the run button will be stop while the loop is running
            //if this condition is meant the GUI text is set to the appropriate state
            this.runButton.setText("START");
            this.textField.setText("to continue.");
        }
    }

}