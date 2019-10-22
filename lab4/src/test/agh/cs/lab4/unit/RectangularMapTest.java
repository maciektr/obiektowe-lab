package agh.cs.lab4.unit;

import agh.cs.lab4.RectangularMap;
import org.junit.Assert;
import org.junit.Test;

public class RectangularMapTest {
    @Test
    public void testToString(){
        Assert.assertEquals(" y\\x  0 1 2 3 4 5 6 7 8 910\r\n" +
                "  6: -----------------------\r\n" +
                "  5: | | | | | | | | | | | |\r\n" +
                "  4: | | | | | | | | | | | |\r\n" +
                "  3: | | | | | | | | | | | |\r\n" +
                "  2: | | | | | | | | | | | |\r\n" +
                "  1: | | | | | | | | | | | |\r\n" +
                "  0: | | | | | | | | | | | |\r\n" +
                " -1: -----------------------\r\n",
                new RectangularMap(10,5).toString() );
    }
}
