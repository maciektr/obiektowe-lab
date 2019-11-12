package agh.cs.lab.integration;

import agh.cs.lab.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class IntegrationAnimalTest {
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
    public void testPosition(){
        RectangularMap map = new RectangularMap(10,5);
        Animal zwierze = new Animal(map);
        map.place(zwierze);

        String[] orders = {"r","l","right","left","right","right"};
        MoveDirection[] parsedMoves = new OptionsParser().parse(orders);
        map.run(parsedMoves);

        Assert.assertEquals(zwierze.getPosition(), new Vector2d(2,2));
    }

    @Test
    public void testStayOnMap(){
        RectangularMap map = new RectangularMap(3,3);
        Animal zwierze = new Animal(map);
        map.place(zwierze);

        String[] orders = {"f","f","f","f","f", "r", "f","f","f","f","f"};
        MoveDirection[] parsedMoves = new OptionsParser().parse(orders);
        map.run(parsedMoves);

        Assert.assertEquals(zwierze.getPosition(), new Vector2d(3,3));
    }

    @Test
    public void testParser(){
        OptionsParser parser = new OptionsParser();
        String[] orders = {"f","right","f","backward","l","left"};
        MoveDirection[] parsedMoves = parser.parse(orders);
        MoveDirection[] manualParsing = {MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.FORWARD,MoveDirection.BACKWARD,MoveDirection.LEFT,MoveDirection.LEFT};
        Assert.assertArrayEquals(parsedMoves,manualParsing);
    }


}
