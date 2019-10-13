package agh.cs.lab3;

public class Car {
    private MapDirection direction = MapDirection.NORTH;
    private Position position = new Position(2,2);

    public String toString(){
        return "Pozycja: "+position+" Kierunek: "+direction;
    }

    private static final Position mapLowerLeft = new Position(0,0);
    private static final Position mapUpperRight = new Position(4,4);
    private boolean isOnMap(Position x){
        return x.follows(mapLowerLeft) && x.precedes(mapUpperRight);
    }

    void move(MoveDirection direction){
        switch(direction){
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case FORWARD:
            case BACKWARD:
                Position afterMove = this.position.add(this.direction.toUnitVector());
                if(isOnMap(afterMove))
                    this.position = afterMove;
                break;
            default:
                break;
        }
    }
}
