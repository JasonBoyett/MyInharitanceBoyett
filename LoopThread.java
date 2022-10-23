/*
* Jason Boyett - jaboye2448
* CIT 4423 01
* October 23, 2022
* mac OS
*/
public class LoopThread extends Thread {//creates an infinite loop that changes the colors of random oval buttons in an array
    MyOval[] ovals;//an array of custom oval buttons
    double tickOffset;//each oval button has a unique tick rate tick offset acts as a multiplier for that tick rate
    //this allows for easy adjustments

    public LoopThread(MyOval[] ovals, double tickOffset) {//constructor fot the thread
        this.ovals = ovals;
        this.tickOffset = tickOffset;
        if(this.tickOffset <= 0){
            this.tickOffset = 10;
        }//prevents runtime errors if the tick offset is zero or less
        this.setDaemon(true);//the loop thread needs to be a daemon thread so that the program can easily exited
        //since loop will never complete until it is interrupted if it was a user thread the program couldn't be easily closed
        this.setPriority(Thread.MIN_PRIORITY);
        //because the loop thread will never end it needs to be the lowest priority so other actions can happen while it's running
    }

    @Override
    public void run() {//the run method of the thread

        try {//try catch required to allow the thread to sleep
            while (true) {//this is an infinite loop. The reason it runs on its own thread is so that it can be interrupted
                this.ovals[(int) (Math.random() * ovals.length)].changeColor();
                Thread.sleep((long)(ovals[0].getTickRate() * this.tickOffset));
            }

        } catch (InterruptedException e) {
            this.interrupt();//since this thread runs an infinite loop it is meant to be interrupted
        }
    }

}
