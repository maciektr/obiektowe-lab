package agh.cs.lab.unit;

import agh.cs.lab.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UnboundedMapTest {
    List<Rock> rocks;
    @Before
    public void setRocks(){
        this.rocks= new ArrayList<Rock>();
        this.rocks.add(new Rock(new Vector2d(-4,-4)));
        this.rocks.add(new Rock(new Vector2d(7,7)));
        this.rocks.add(new Rock(new Vector2d(3,6)));
        this.rocks.add(new Rock(new Vector2d(2,0)));
    }
    @Test
    public void testToString(){
        Assert.assertEquals(" y\\x  0 1\r\n" +
                        "  2: -----\r\n" +
                        "  1: | | |\r\n" +
                        "  0: | | |\r\n" +
                        " -1: -----\r\n",
                new UnboundedMap(new ArrayList<Rock>()).toString() );
        Assert.assertEquals(" y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                        "  8: -------------------------\r\n" +
                        "  7: | | | | | | | | | | | |R|\r\n" +
                        "  6: | | | | | | | |R| | | | |\r\n" +
                        "  5: | | | | | | | | | | | | |\r\n" +
                        "  4: | | | | | | | | | | | | |\r\n" +
                        "  3: | | | | | | | | | | | | |\r\n" +
                        "  2: | | | | | | | | | | | | |\r\n" +
                        "  1: | | | | | | | | | | | | |\r\n" +
                        "  0: | | | | | | |R| | | | | |\r\n" +
                        " -1: | | | | | | | | | | | | |\r\n" +
                        " -2: | | | | | | | | | | | | |\r\n" +
                        " -3: | | | | | | | | | | | | |\r\n" +
                        " -4: |R| | | | | | | | | | | |\r\n" +
                        " -5: -------------------------\r\n",
                new UnboundedMap(rocks).toString() );
        List<Rock> r = new ArrayList<Rock>();
        r.add(new Rock(new Vector2d(-1,1)));
        r.add(new Rock(new Vector2d(1,-1)));
        Assert.assertEquals(" y\\x -1 0 1\r\n" +
                "  2: -------\r\n" +
                "  1: |R| | |\r\n" +
                "  0: | | | |\r\n" +
                " -1: | | |R|\r\n" +
                " -2: -------\r\n",
                new UnboundedMap(r).toString());
    }

    @Test
    public void testCanMoveTo(){

        UnboundedMap map = new UnboundedMap(rocks);
        Assert.assertFalse(map.canMoveTo(new Vector2d(-4,-4)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(7, 7)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(3, 6)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(2, 0)));

        Assert.assertTrue(map.canMoveTo(new Vector2d(-1, -1)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(0,0)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(Integer.MIN_VALUE, Integer.MAX_VALUE)));


        map.place(new Animal(map));
        map.place(new Animal(map, new Vector2d(13,4)));

        Assert.assertFalse(map.canMoveTo(new Vector2d(-4,-4)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(7, 7)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(3, 6)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(2, 0)));

        Assert.assertFalse(map.canMoveTo(new Vector2d(13, 4)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(2,2)));

        Assert.assertTrue(map.canMoveTo(new Vector2d(2,3)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(0,0)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(-1, -1)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(12, 5)));
    }

    @Test
    public void testIsOccupied(){
        UnboundedMap map = new UnboundedMap(rocks);

        Assert.assertFalse(map.isOccupied(new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE)));
        Assert.assertFalse(map.isOccupied(new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE)));
        Assert.assertFalse(map.isOccupied(new Vector2d(Integer.MIN_VALUE, Integer.MAX_VALUE)));
        Assert.assertFalse(map.isOccupied(new Vector2d(Integer.MAX_VALUE, Integer.MIN_VALUE)));

        Assert.assertFalse(map.isOccupied(new Vector2d(-1, -1)));
        Assert.assertFalse(map.isOccupied(new Vector2d(0,0)));

        Assert.assertTrue(map.isOccupied(new Vector2d(-4,-4)));
        Assert.assertTrue(map.isOccupied(new Vector2d(7, 7)));
        Assert.assertTrue(map.isOccupied(new Vector2d(3, 6)));
        Assert.assertTrue(map.isOccupied(new Vector2d(2, 0)));

        map.place(new Animal(map));
        map.place(new Animal(map, new Vector2d(13,4)));
        map.place(new Animal(map, new Vector2d(Integer.MAX_VALUE, Integer.MIN_VALUE)));

        Assert.assertTrue(map.isOccupied(new Vector2d(2,2)));
        Assert.assertTrue(map.isOccupied(new Vector2d(13,4)));
        Assert.assertTrue(map.isOccupied(new Vector2d(Integer.MAX_VALUE, Integer.MIN_VALUE)));

        Assert.assertFalse(map.isOccupied(new Vector2d(Integer.MIN_VALUE, Integer.MAX_VALUE)));
        Assert.assertFalse(map.isOccupied(new Vector2d(0,0)));
        Assert.assertFalse(map.isOccupied(new Vector2d(2,3)));
        Assert.assertFalse(map.isOccupied(new Vector2d(0,-1)));

        map.place(new Animal(map, new Vector2d(0,0)));
        Assert.assertTrue(map.isOccupied(new Vector2d(0,0)));
    }

    @Test
    public void testObjectAt() {
        UnboundedMap map = new UnboundedMap(rocks);
        Assert.assertNull(map.objectAt(new Vector2d(0,0)));
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal(map));
        animals.add(new Animal(map, new Vector2d(13, 4)));
        for(Animal a : animals)
                map.place(a);
        for(Animal a : animals)
            Assert.assertEquals(map.objectAt(a.getPosition()), a);

        for(Rock r : rocks)
            Assert.assertEquals(map.objectAt(r.getPosition()), r);
    }
}
