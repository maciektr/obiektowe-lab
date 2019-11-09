package agh.cs.lab;

import java.util.List;

public class UnboundedMap extends AbstractWorldMap implements IWorldMap{
    static private int INF = Integer.MAX_VALUE;
    protected Vector2d mapLowerLeft;
    protected Vector2d mapUpperRight;

    public UnboundedMap(List<Rock> rocks){
        this.mapLowerLeft = new Vector2d(INF,INF);
        this.mapUpperRight = new Vector2d(-INF,-INF);
        for(Rock r : rocks){
            super.objects.put(r.getPosition(), r);
            this.mapLowerLeft = this.mapLowerLeft.lowerLeft(r.getPosition());
            this.mapUpperRight = this.mapUpperRight.upperRight(r.getPosition());
        }
    }

    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);

        for(IMapElement a: super.animals) {
            mapLowerLeft = mapLowerLeft.lowerLeft(a.getPosition());
            mapUpperRight = mapUpperRight.upperRight(a.getPosition());
        }
    if(mapUpperRight.equals(new Vector2d(-INF, -INF)) || mapLowerLeft.equals(new Vector2d(INF,INF)))
        return visualizer.draw(new Vector2d(0,0), new Vector2d(1,1));
    return visualizer.draw(mapLowerLeft, mapUpperRight);
    }
}
