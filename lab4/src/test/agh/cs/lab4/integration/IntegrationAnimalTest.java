package agh.cs.lab4.integration;

import agh.cs.lab4.Animal;
import agh.cs.lab4.MoveDirection;
import agh.cs.lab4.OptionsParser;
import org.junit.Assert;
import org.junit.Test;
//funkcję uruchamiającą test
public class IntegrationAnimalTest {
    @Test
    public void testOrientation(){
        Animal zwierze = new Animal();
        OptionsParser parser = new OptionsParser();
        String[] orders = {"r","l","right","left","right","right"};
        MoveDirection[] parsedMoves = parser.parse(orders);
        for(MoveDirection move : parsedMoves){
           zwierze.move(move);
        }
        Assert.assertEquals(zwierze.toString(),"Pozycja: (2,2) Kierunek: Południe");
    }
    @Test
    public void testPosition(){
        Animal zwierze = new Animal();
        OptionsParser parser = new OptionsParser();
        String[] orders = {"r","l","f","right","left","right","right","b","l","f"};
        MoveDirection[] parsedMoves = parser.parse(orders);
        for(MoveDirection move : parsedMoves){
            zwierze.move(move);
        }
        Assert.assertEquals(zwierze.toString(),"Pozycja: (3,4) Kierunek: Wschód");
    }
    @Test
    public void testStayOnMap(){
        Animal zwierze = new Animal();
        OptionsParser parser = new OptionsParser();
        String[] orders = {"f","f","f","f","f","f"};
        MoveDirection[] parsedMoves = parser.parse(orders);
        for(MoveDirection move : parsedMoves){
            zwierze.move(move);
        }
        Assert.assertNotEquals(zwierze.toString(),"Pozycja: (2,8) Kierunek: Północ");
    }
    @Test
    public void testParser(){
        Animal zwierze = new Animal();
        OptionsParser parser = new OptionsParser();
        String[] orders = {"f","right","f","backward","l","left"};
        MoveDirection[] parsedMoves = parser.parse(orders);
        MoveDirection[] manualParsing = {MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.FORWARD,MoveDirection.BACKWARD,MoveDirection.LEFT,MoveDirection.LEFT};
        Assert.assertEquals(parsedMoves,manualParsing);
    }
}
