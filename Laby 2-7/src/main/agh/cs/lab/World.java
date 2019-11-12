package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String []args){
        try {
            MoveDirection[] directions = new OptionsParser().parse(args);
            List<Rock> rocks = new ArrayList<Rock>();
            rocks.add(new Rock(new Vector2d(-1, 1)));
            rocks.add(new Rock(new Vector2d(1, -1)));
            IWorldMap map = new UnboundedMap(rocks);
            System.out.println(map.toString());
            map.place(new Animal(map));
            map.place(new Animal(map,new Vector2d(3,4)));
        } catch(IllegalArgumentException ex) {
            System.out.println(ex.toString());
        }

    }

}
