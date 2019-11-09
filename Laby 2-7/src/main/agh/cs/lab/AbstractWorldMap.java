package agh.cs.lab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap {
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

    public void run(MoveDirection[] directions){
        for(int i = 0; i<directions.length; i++){
            Animal act = animals.get(i % (animals.size()));
            objects.remove(act.getPosition());
            act.move(directions[i]);
            objects.put(act.getPosition(), act);

            System.out.println(this.toString());
        }
    }
    public boolean place(Animal animal){
        if(this.isOccupied(animal.getPosition()))
            throw new IllegalArgumentException("Position "+animal.getPosition().toString() + " is already occupied.");
        this.animals.add(animal);
        this.objects.put(animal.getPosition(), animal);
        return true;
    }
}
