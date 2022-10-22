public class LoopThread extends Thread {
    MyOval ovals[];    
    int tickOffset;
    public LoopThread(MyOval[] ovals, int tickOffset){
        this.ovals = ovals;
        this.tickOffset = tickOffset;
    }
    
    public void run() {
        
        try {
            int i = 0;
            while(i < ovals.length){
                this.ovals[(int)(Math.random() * ovals.length)].changeColor();
                Thread.sleep(ovals[i].getTickRate() * this.tickOffset);
                i++;
                if(i >= this.ovals.length){
                    i = 0;
                }
            }
      
        } 
        catch (InterruptedException e) {
            new LoopThread(ovals, tickOffset);
        }
    }
    
}
