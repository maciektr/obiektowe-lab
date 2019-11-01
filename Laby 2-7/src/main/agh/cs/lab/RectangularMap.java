package agh.cs.lab;

public class RectangularMap extends AbstractWorldMap implements IWorldMap{
    private  final Vector2d mapLowerLeft = new Vector2d(0,0);
    private final Vector2d mapUpperRight;

    public RectangularMap(int width, int height){
        mapUpperRight = new Vector2d(width, height);
    }

    public boolean canMoveTo(Vector2d positionAfterMove){
        return super.canMoveTo(positionAfterMove)
                && positionAfterMove.follows(mapLowerLeft)
                && positionAfterMove.precedes(mapUpperRight);
    }

    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(mapLowerLeft,mapUpperRight);
    }
}
