package agh.cs.lab4;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    private  final Vector2d mapLowerLeft = new Vector2d(0,0);
    private final Vector2d mapUpperRight;
    private List<Animal> animals = new ArrayList<Animal>();

    public RectangularMap(int width, int height){
        mapUpperRight = new Vector2d(width, height);
    }

    public boolean canMoveTo(Vector2d positionAfterMove){
        return !this.isOccupied(positionAfterMove)
                && positionAfterMove.follows(mapLowerLeft)
                && positionAfterMove.precedes(mapUpperRight);
    }

    public boolean place(Animal animal){
        if(this.isOccupied(animal.getPosition()))
            return false;
        this.animals.add(animal);
        return true;
    }

    public void run(MoveDirection[] directions){
        for(int i = 0; i<directions.length; i++){
            animals.get(i % (animals.size())).move(directions[i]);
            System.out.println(this.toString());
        }
    }

    public boolean isOccupied(Vector2d position){
        for(Animal a:animals)
            if(position.equals(a.getPosition()))
                return true;
        return false;
    }

    public Object objectAt(Vector2d position){
        for(Animal a:animals)
            if(position.equals(a.getPosition()))
                return a;
        return null;
    }
    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(mapLowerLeft,mapUpperRight);
    }
}
