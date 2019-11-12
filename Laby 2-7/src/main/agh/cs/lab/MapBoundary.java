package agh.cs.lab;

import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    SortedSet<Vector2d> xAxis = new TreeSet<Vector2d>(new Vector2dComparatorFirst());
    SortedSet<Vector2d> yAxis = new TreeSet<Vector2d>(new Vector2dComparatorSecond());

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        xAxis.remove(oldPosition);
        yAxis.remove(oldPosition);

        xAxis.add(newPosition);
        yAxis.add(newPosition);
    }

    public void place( Vector2d position){
        xAxis.add(position);
        yAxis.add(position);
    }

    public Vector2d getBoundaryLowerLeft(){
        if(xAxis.isEmpty() || yAxis.isEmpty())
            return new Vector2d(0,0);
        return new Vector2d(xAxis.first().x, yAxis.first().y);
    }

    public Vector2d getBoundaryUpperRight() {
        if(xAxis.isEmpty() || yAxis.isEmpty())
            return new Vector2d(1,1);
        return new Vector2d(xAxis.last().x, yAxis.last().y);
    }
}
