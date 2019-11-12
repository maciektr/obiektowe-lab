package agh.cs.lab.integration;

import agh.cs.lab.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class IntegrationUnboundedMapTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void stepOnRock(){
        List<Rock> rocks = new ArrayList<Rock>();
        rocks.add(new Rock(new Vector2d(1,1)));
        rocks.add(new Rock(new Vector2d(3,3)));
        rocks.add(new Rock(new Vector2d(0,1)));
        UnboundedMap map = new UnboundedMap(rocks);
        Animal a = new Animal(map, new Vector2d(1,1));
        Animal b = new Animal(map, new Vector2d(2,2));
        Animal c = new Animal(map, new Vector2d(0,0));
//        Assert.assertFalse(map.place(a));
        map.place(b);map.place(c);

        Assert.assertFalse(map.canMoveTo(new Vector2d(1,1)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(2,2)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(0,0)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(3,3)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(0,1)));

        Assert.assertNotEquals(a, map.objectAt(new Vector2d(1,1)));
        Assert.assertEquals(b,map.objectAt(new Vector2d(2,2)));

        Assert.assertEquals(c,map.objectAt(new Vector2d(0,0)));

        c.move(MoveDirection.FORWARD);
        Assert.assertNotEquals(c, map.objectAt(new Vector2d(0,1)));
        Assert.assertEquals(c, map.objectAt(new Vector2d(0,0)));

//        b.move(MoveDirection.RIGHT);
//        b.move(MoveDirection.FORWARD);
//        Assert.assertEquals(b, map.objectAt(new Vector2d(3,2)));
    }

    @Test
    public void testRun(){
        String[] orders = {"f","b", "r","l","f","f","r","r","f","f","f","f","f","f","f","f"};
        List<Rock> rocks = new ArrayList<Rock>();
        rocks.add(new Rock(new Vector2d(-4,-4)));
        rocks.add(new Rock(new Vector2d(7,7)));
        rocks.add(new Rock(new Vector2d(3,6)));
        rocks.add(new Rock(new Vector2d(2,0)));

        MoveDirection[] directions = new OptionsParser().parse(orders);
        IWorldMap map = new UnboundedMap(rocks);

        map.place(new Animal(map));
        map.place(new Animal(map,new Vector2d(3,4)));
        map.run(directions);

        Assert.assertEquals(" y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | | | | | | |\r\n" +
                "  4: | | | | | | | |N| | | | |\r\n" +
                "  3: | | | | | | |N| | | | | |\r\n" +
                "  2: | | | | | | | | | | | | |\r\n" +
                "  1: | | | | | | | | | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n" +
                "\r\n" +
                " y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | | | | | | |\r\n" +
                "  4: | | | | | | | | | | | | |\r\n" +
                "  3: | | | | | | |N|N| | | | |\r\n" +
                "  2: | | | | | | | | | | | | |\r\n" +
                "  1: | | | | | | | | | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n" +
                "\r\n" +
                " y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | | | | | | |\r\n" +
                "  4: | | | | | | | | | | | | |\r\n" +
                "  3: | | | | | | |E|N| | | | |\r\n" +
                "  2: | | | | | | | | | | | | |\r\n" +
                "  1: | | | | | | | | | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n" +
                "\r\n" +
                " y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | | | | | | |\r\n" +
                "  4: | | | | | | | | | | | | |\r\n" +
                "  3: | | | | | | |E|W| | | | |\r\n" +
                "  2: | | | | | | | | | | | | |\r\n" +
                "  1: | | | | | | | | | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n" +
                "\r\n" +
                " y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | | | | | | |\r\n" +
                "  4: | | | | | | | | | | | | |\r\n" +
                "  3: | | | | | | |E|W| | | | |\r\n" +
                "  2: | | | | | | | | | | | | |\r\n" +
                "  1: | | | | | | | | | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n" +
                "\r\n" +
                " y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | | | | | | |\r\n" +
                "  4: | | | | | | | | | | | | |\r\n" +
                "  3: | | | | | | |E|W| | | | |\r\n" +
                "  2: | | | | | | | | | | | | |\r\n" +
                "  1: | | | | | | | | | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n" +
                "\r\n" +
                " y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | | | | | | |\r\n" +
                "  4: | | | | | | | | | | | | |\r\n" +
                "  3: | | | | | | |S|W| | | | |\r\n" +
                "  2: | | | | | | | | | | | | |\r\n" +
                "  1: | | | | | | | | | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n" +
                "\r\n" +
                " y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | | | | | | |\r\n" +
                "  4: | | | | | | | | | | | | |\r\n" +
                "  3: | | | | | | |S|N| | | | |\r\n" +
                "  2: | | | | | | | | | | | | |\r\n" +
                "  1: | | | | | | | | | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n" +
                "\r\n" +
                " y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | | | | | | |\r\n" +
                "  4: | | | | | | | | | | | | |\r\n" +
                "  3: | | | | | | | |N| | | | |\r\n" +
                "  2: | | | | | | |S| | | | | |\r\n" +
                "  1: | | | | | | | | | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n" +
                "\r\n" +
                " y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | | | | | | |\r\n" +
                "  4: | | | | | | | |N| | | | |\r\n" +
                "  3: | | | | | | | | | | | | |\r\n" +
                "  2: | | | | | | |S| | | | | |\r\n" +
                "  1: | | | | | | | | | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n" +
                "\r\n" +
                " y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | | | | | | |\r\n" +
                "  4: | | | | | | | |N| | | | |\r\n" +
                "  3: | | | | | | | | | | | | |\r\n" +
                "  2: | | | | | | | | | | | | |\r\n" +
                "  1: | | | | | | |S| | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n" +
                "\r\n" +
                " y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | |N| | | | |\r\n" +
                "  4: | | | | | | | | | | | | |\r\n" +
                "  3: | | | | | | | | | | | | |\r\n" +
                "  2: | | | | | | | | | | | | |\r\n" +
                "  1: | | | | | | |S| | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n" +
                "\r\n" +
                " y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | |N| | | | |\r\n" +
                "  4: | | | | | | | | | | | | |\r\n" +
                "  3: | | | | | | | | | | | | |\r\n" +
                "  2: | | | | | | | | | | | | |\r\n" +
                "  1: | | | | | | |S| | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n" +
                "\r\n" +
                " y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | |N| | | | |\r\n" +
                "  4: | | | | | | | | | | | | |\r\n" +
                "  3: | | | | | | | | | | | | |\r\n" +
                "  2: | | | | | | | | | | | | |\r\n" +
                "  1: | | | | | | |S| | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n" +
                "\r\n" +
                " y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | |N| | | | |\r\n" +
                "  4: | | | | | | | | | | | | |\r\n" +
                "  3: | | | | | | | | | | | | |\r\n" +
                "  2: | | | | | | | | | | | | |\r\n" +
                "  1: | | | | | | |S| | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n" +
                "\r\n" +
                " y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                "  8: -------------------------\r\n" +
                "  7: | | | | | | | | | | | |R|\r\n" +
                "  6: | | | | | | | |R| | | | |\r\n" +
                "  5: | | | | | | | |N| | | | |\r\n" +
                "  4: | | | | | | | | | | | | |\r\n" +
                "  3: | | | | | | | | | | | | |\r\n" +
                "  2: | | | | | | | | | | | | |\r\n" +
                "  1: | | | | | | |S| | | | | |\r\n" +
                "  0: | | | | | | |R| | | | | |\r\n" +
                " -1: | | | | | | | | | | | | |\r\n" +
                " -2: | | | | | | | | | | | | |\r\n" +
                " -3: | | | | | | | | | | | | |\r\n" +
                " -4: |R| | | | | | | | | | | |\r\n" +
                " -5: -------------------------\r\n\r\n", outContent.toString());
    }

    @Test
    public void testObserver(){
        //Check if map boundry registers move of animal
        List<Rock> r = new ArrayList<Rock>();
        r.add(new Rock(new Vector2d(-4,-4)));
        r.add(new Rock(new Vector2d(3,6)));
        r.add(new Rock(new Vector2d(2,0)));
        UnboundedMap map = new UnboundedMap(r);
        Animal a = new Animal(map, new Vector2d(6,6));
        map.place(a);
        a.move(MoveDirection.FORWARD);
        a.move(MoveDirection.RIGHT);
        a.move(MoveDirection.FORWARD);

        Assert.assertEquals(" y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                        "  8: -------------------------\r\n" +
                        "  7: | | | | | | | | | | | |E|\r\n" +
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
                map.toString() );
        //Check if animal can step on rock
        r.add(new Rock(new Vector2d(7,7)));
        map = new UnboundedMap(r);
        a = new Animal(map, new Vector2d(6,6));
        map.place(a);
        a.move(MoveDirection.FORWARD);
        a.move(MoveDirection.RIGHT);
        a.move(MoveDirection.FORWARD);
        Assert.assertEquals(" y\\x -4-3-2-1 0 1 2 3 4 5 6 7\r\n" +
                        "  8: -------------------------\r\n" +
                        "  7: | | | | | | | | | | |E|R|\r\n" +
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
                map.toString() );


    }
}
