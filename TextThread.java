import javax.swing.JButton;
import javax.swing.JTextField;

public class TextThread extends Thread{
    JTextField firstField;
    JTextField secondField;
    JButton runButton;

    public TextThread(JTextField firstField, JTextField secondField, JButton runButton){
        this.firstField = firstField;
        this.secondField = secondField;
        this.runButton = runButton;
    }

    @Override
    public void run(){
        if(this.runButton.getText().equalsIgnoreCase("start")){
            this.runButton.setText("STOP");
            this.firstField.setText("Press stop ...");
            this.secondField.setText("to stop.");
        }
        else if(this.runButton.getText().equalsIgnoreCase("stop")){
            this.runButton.setText("START");
            this.firstField.setText("Press start ...");
            this.secondField.setText("to start.");
        }
    }
    
}
