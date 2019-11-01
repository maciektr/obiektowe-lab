package agh.cs.lab.unit;

import agh.cs.lab.*;
import org.junit.Assert;
import org.junit.Test;

public class AnimalTest {
    @Test
    public void testConstructor(){
        IWorldMap map = new RectangularMap(10, 5);
        Assert.assertEquals(new Animal(map,new Vector2d(2,2)).getPosition(), new Animal(map).getPosition());
        Assert.assertEquals( new Vector2d(7,4), new Animal(map,new Vector2d(7,4)).getPosition());
    }

    @Test
    public void testToString(){
        IWorldMap map = new RectangularMap(7,8);
        Animal a = new Animal(map);
        Assert.assertEquals("N",a.toString());
        a.move(MoveDirection.RIGHT);
        Assert.assertEquals("E",a.toString());
        a.move(MoveDirection.RIGHT);
        Assert.assertEquals("S",a.toString());
        a.move(MoveDirection.RIGHT);
        Assert.assertEquals("W",a.toString());
    }



}
