package agh.cs.lab3;

public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public String toString(){
        return "Pozycja: "+ position +" Kierunek: "+direction;
    }

    private static final Vector2d mapLowerLeft = new Vector2d(0,0);
    private static final Vector2d mapUpperRight = new Vector2d(4,4);
    private boolean isOnMap(Vector2d x){
        return x.follows(mapLowerLeft) && x.precedes(mapUpperRight);
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
                MapDirection thisMoveDirection = this.direction;
                if(moveDirection==MoveDirection.BACKWARD)
                    thisMoveDirection = thisMoveDirection.previous().previous();
                Vector2d afterMove = this.position.add(thisMoveDirection.toUnitVector());
                if(isOnMap(afterMove))
                    this.position = afterMove;
                break;
            default:
                break;
        }
    }
}
