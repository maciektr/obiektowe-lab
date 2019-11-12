package agh.cs.lab;

import java.util.*;

public abstract class AbstractWorldMap implements IPositionChangeObserver {
    protected List<Animal> animals = new ArrayList<Animal>();
    protected Map<Vector2d, IMapElement> objects = new HashMap<>();

    public boolean canMoveTo(Vector2d positionAfterMove){
        return !this.isOccupied(positionAfterMove);
    }
    public boolean isOccupied(Vector2d position){
        return this.objectAt(position)!=null;
    }
    public Object objectAt(Vector2d position){
        return objects.get(position);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        IMapElement a = objects.get(oldPosition);
        objects.remove(oldPosition);
        objects.put(newPosition, a);
    }

    public void run(MoveDirection[] directions){
        for(int i = 0; i<directions.length; i++){
            Animal act = animals.get(i % (animals.size()));
            act.move(directions[i]);
            System.out.println(this.toString());
        }
    }

    public boolean place(Animal animal){
        if(this.isOccupied(animal.getPosition()))
            throw new IllegalArgumentException("Position "+animal.getPosition().toString() + " is already occupied.");
        animal.addObserver(this);
        this.animals.add(animal);
        this.objects.put(animal.getPosition(), animal);
        return true;
    }
}
