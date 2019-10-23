package agh.cs.lab4.unit;

import agh.cs.lab4.Animal;
import agh.cs.lab4.RectangularMap;
import agh.cs.lab4.Vector2d;
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

    @Test
    public void testCanMoveTo(){
        RectangularMap map = new RectangularMap(13,4);
        Assert.assertFalse(map.canMoveTo(new Vector2d(1,5)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(14, 1)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(1, -1)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(-1, -1)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(13, 4)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(0,0)));

        map.place(new Animal(map));
        map.place(new Animal(map, new Vector2d(13,4)));

        Assert.assertFalse(map.canMoveTo(new Vector2d(1,5)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(14, 1)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(1, -1)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(-1, -1)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(13, 4)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(2,2)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(2,3)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(0,0)));

    }

    @Test
    public void testIsOccupied(){
        RectangularMap map = new RectangularMap(13,4);
        map.place(new Animal(map));
        map.place(new Animal(map, new Vector2d(13,4)));
        Assert.assertTrue(map.isOccupied(new Vector2d(2,2)));
        Assert.assertTrue(map.isOccupied(new Vector2d(13,4)));
        Assert.assertFalse(map.isOccupied(new Vector2d(2,3)));
        Assert.assertFalse(map.isOccupied(new Vector2d(0,0)));
        map.place(new Animal(map, new Vector2d(0,0)));
        Assert.assertTrue(map.isOccupied(new Vector2d(0,0)));
    }

    @Test
    public void testObjectAt() {
        RectangularMap map = new RectangularMap(13, 4);
        Assert.assertNull(map.objectAt(new Vector2d(0,0)));
        Animal a1 = new Animal(map);
        map.place(a1);
        Animal a2 = new Animal(map, new Vector2d(13, 4));
        map.place(a2);
        Assert.assertEquals(map.objectAt(new Vector2d(13,4)), a2);
        Assert.assertEquals(map.objectAt(new Vector2d(2,2)), a1);
    }
    }
