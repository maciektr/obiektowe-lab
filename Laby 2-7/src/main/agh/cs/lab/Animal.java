package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);
    private IWorldMap map;

    public Animal(IWorldMap map){
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
    }

    protected List<IPositionChangeObserver> observers = new ArrayList<IPositionChangeObserver>();
    void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition,newPosition);
        }
    }

    public Vector2d getPosition(){
        return position;
    }

    public String toString(){
        switch(direction){
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            case WEST:
                return "W";
        }
        return "E";
    }

    public void move(MoveDirection moveDirection){
        switch(moveDirection){
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case FORWARD:
            case BACKWARD:
                Vector2d thisMove = this.direction.toUnitVector();
                if(moveDirection == MoveDirection.BACKWARD)
                    thisMove = thisMove.opposite();
                Vector2d afterMove = this.position.add(thisMove);

                if(this.map.canMoveTo(afterMove)){
                    this.positionChanged(this.position, afterMove);
                    this.position = afterMove;
                }
                break;
        }
    }

}
