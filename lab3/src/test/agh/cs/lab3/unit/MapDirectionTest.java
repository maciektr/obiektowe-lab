package agh.cs.lab3.unit;

import agh.cs.lab3.MapDirection;
import org.junit.Assert;
import org.junit.Test;

//red green refactor

public class MapDirectionTest {
    @Test
    public void testNext(){
        Assert.assertEquals(MapDirection.SOUTH, MapDirection.EAST.next());
        Assert.assertEquals(MapDirection.EAST, MapDirection.NORTH.next());
        Assert.assertEquals(MapDirection.NORTH, MapDirection.WEST.next());
        Assert.assertEquals(MapDirection.WEST, MapDirection.SOUTH.next());
    }

    @Test
    public void testPrevious(){
        Assert.assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous());
        Assert.assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous());
        Assert.assertEquals(MapDirection.NORTH, MapDirection.EAST.previous());
        Assert.assertEquals(MapDirection.WEST, MapDirection.NORTH.previous());
    }

}
