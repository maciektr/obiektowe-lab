package agh.cs.lab;

import java.util.List;

public class UnboundedMap extends AbstractWorldMap implements IWorldMap{
    private final List<Rock> rocks;

    public UnboundedMap(List<Rock> rocks){
        this.rocks = rocks;
    }

    public Object objectAt(Vector2d position){
        Object object = super.objectAt(position);
        if(object!=null)
            return object;
        for(Rock r : rocks)
            if(position.equals(r.getPosition()))
                return r;
        return null;
    }

    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        Vector2d mapLoweLeft = new Vector2d(0,0);
        Vector2d mapUpperRight = new Vector2d(1,1);

        for(IMapElement a: animals) {
            mapLoweLeft = mapLoweLeft.lowerLeft(a.getPosition());
            mapUpperRight = mapUpperRight.upperRight(a.getPosition());
        }
        for(IMapElement a : rocks){
            mapLoweLeft = mapLoweLeft.lowerLeft(a.getPosition());
            mapUpperRight = mapUpperRight.upperRight(a.getPosition());
        }
    return visualizer.draw(mapLoweLeft, mapUpperRight);
    }
}
