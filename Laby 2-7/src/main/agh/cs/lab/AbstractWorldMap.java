package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;

public class AbstractWorldMap {
    protected List<Animal> animals = new ArrayList<Animal>();

    public boolean canMoveTo(Vector2d positionAfterMove){
        return !this.isOccupied(positionAfterMove);
    }
    public boolean isOccupied(Vector2d position){
        return this.objectAt(position)!=null;
    }
    public Object objectAt(Vector2d position){
        for(Animal a:animals)
            if(position.equals(a.getPosition()))
                return a;
        return null;
    }

    public void run(MoveDirection[] directions){
        for(int i = 0; i<directions.length; i++){
            animals.get(i % (animals.size())).move(directions[i]);
            System.out.println(this.toString());
        }
    }
    public boolean place(Animal animal){
        if(this.isOccupied(animal.getPosition()))
            return false;
        this.animals.add(animal);
        return true;
    }

/*
    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(mapLowerLeft,mapUpperRight);
    }
*/
}
