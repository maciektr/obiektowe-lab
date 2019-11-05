package agh.cs.lab;

import java.util.List;

public class UnboundedMap extends AbstractWorldMap implements IWorldMap{
//    private final List<Rock> rocks;
    protected Vector2d mapLowerLeft;// = new Vector2d(0,0);
    protected Vector2d mapUpperRight;// = new Vector2d(1,1);

    public UnboundedMap(List<Rock> rocks){
//        this.rocks = rocks;
        if(rocks.size() == 0){
            this.mapLowerLeft =  new Vector2d(0,0);
            this.mapUpperRight = new Vector2d(1,1);
            return;
        }
        this.mapLowerLeft = rocks.get(0).getPosition();
        this.mapUpperRight = rocks.get(0).getPosition();
        for(Rock r : rocks){
            super.objects.put(r.getPosition(), r);
            this.mapLowerLeft = this.mapLowerLeft.lowerLeft(r.getPosition());
            this.mapUpperRight = this.mapUpperRight.upperRight(r.getPosition());
        }
    }
    /*
    public Object objectAt(Vector2d position){
        Object object = super.objectAt(position);
        if(object!=null)
            return object;
        for(Rock r : rocks)
            if(position.equals(r.getPosition()))
                return r;
        return null;
    }
    */
    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
/*
        for(IMapElement a: animals) {
            mapLowerLeft = mapLowerLeft.lowerLeft(a.getPosition());
            mapUpperRight = mapUpperRight.upperRight(a.getPosition());
        }
*/
    return visualizer.draw(mapLowerLeft.lowerLeft(super.animalsLowerLeft), mapUpperRight.upperRight(super.animalsUpperRight));
    }
}
