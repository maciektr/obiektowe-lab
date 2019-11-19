package agh.cs.lab;

import java.util.List;

public class UnboundedMap extends AbstractWorldMap implements IWorldMap{

    private MapBoundary boundary = new MapBoundary();

    public UnboundedMap(List<Rock> rocks){
        for(Rock r : rocks){
            boundary.place(r);
            super.objects.put(r.getPosition(), r);
        }
    }

    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(boundary.getBoundaryLowerLeft(), boundary.getBoundaryUpperRight());
    }

    public boolean place(Animal animal){
        boundary.place(animal);
        return super.place(animal);
    }
}
