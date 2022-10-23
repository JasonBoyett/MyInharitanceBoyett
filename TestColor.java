import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;
public class TestColor {

    @Test
    public void testColor() {
        MyOval test = new MyOval();

        assertEquals(Color.green, test.getColor());
    }

    @Test
    public void testChangedColor() {
        MyOval test = new MyOval();

        test.changeColor();
        
        assertEquals(Color.RED, test.getColor());
    }
}