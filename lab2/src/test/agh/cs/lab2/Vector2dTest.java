package agh.cs.lab2;

import org.junit.Assert;
import org.junit.Test;

public class Vector2dTest {
    private final Vector2d vector_1_2 = new Vector2d(1,2);
    private final Vector2d vector_0_0 = new Vector2d(0,0);


    @Test
    public void testEquals(){
        Assert.assertEquals(vector_1_2,new Vector2d(0,2).add(new Vector2d(1,0)));
        Assert.assertNotEquals(vector_1_2, new Vector2d(-1,-2));
    }

    @Test
    public void testToString(){
        Assert.assertEquals("(1,2)", vector_1_2.toString());
        Assert.assertNotEquals("(1,2)", new Vector2d(-1,-2).toString());
    }

    @Test
    public void testPrecedes(){
        Assert.assertTrue(new Vector2d(-1,-2).precedes(vector_1_2));
        Assert.assertTrue(vector_0_0.precedes(vector_0_0));
        Assert.assertFalse(vector_1_2.precedes(new Vector2d(-1,-2)));
    }

    @Test
    public void testFollows(){
        Assert.assertFalse(new Vector2d(-1,-2).follows(vector_1_2));
        Assert.assertTrue(vector_0_0.follows(vector_0_0));
        Assert.assertTrue(vector_1_2.follows(new Vector2d(-1,-2)));
    }

    @Test
    public void testUpperRight(){
        Assert.assertEquals(new Vector2d(2,2),vector_1_2.upperRight(new Vector2d(2,1)));
        Assert.assertNotEquals(new Vector2d(1,1),vector_1_2.upperRight(new Vector2d(2,1)));
    }

    @Test
    public void testLowerLeft(){
        Assert.assertNotEquals(new Vector2d(2,2),vector_1_2.lowerLeft(new Vector2d(2,1)));
        Assert.assertEquals(new Vector2d(1,1),vector_1_2.lowerLeft(new Vector2d(2,1)));
    }

    @Test
    public void testAdd(){
        Assert.assertNotEquals(new Vector2d(2,2),vector_1_2.add(new Vector2d(2,1)));
        Assert.assertEquals(new Vector2d(1,1),new Vector2d(-1,2).add(new Vector2d(2,-1)));
        Assert.assertEquals(vector_0_0,vector_0_0.add(vector_0_0));
    }

    @Test
    public void testSubtract(){
        Assert.assertEquals(new Vector2d(-1,1),vector_1_2.substract(new Vector2d(2,1)));
        Assert.assertNotEquals(new Vector2d(1,1),new Vector2d(-1,2).substract(new Vector2d(2,-1)));
        Assert.assertEquals(vector_0_0,vector_0_0.substract(vector_0_0));
    }

    @Test
    public void testOpposite(){
        Assert.assertEquals(new Vector2d(-1,-1), new Vector2d(1,1).opposite());
        Assert.assertEquals(vector_0_0, vector_0_0.opposite());
        Assert.assertNotEquals(new Vector2d(1,1), new Vector2d(1,1).opposite());
    }
}

