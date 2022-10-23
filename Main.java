/*
* Jason Boyett - jaboye2448
* CIT 4423 01
* October 23, 2022
* mac OS
*/

/*
 * this project uses a repository from github.com that creates a circular JButton object
 * here is a link to said repository: https://github.com/lukakralj/Snippets/blob/master/Java/ovalButton/OvalButton.java
 * It is very well written and works fantastically. Please visit and star this repository if you are so inclined
 * the buttons are placed in an array and then displayed in a 5X4 grid
 * on hitting a button the circles will all begin to flash randomly between red and green
 * the buttons will flash based on their own randomized tick rate times a shared tick rate multiplier
 * this project uses multithreading so that the animation of the flashing buttons can run in an infinite loop and can still be interrupted
 */

public class Main{
    public static void main(String[] args){
        MyFrame frame = new MyFrame();
        frame.setVisible(true);
    }
}
