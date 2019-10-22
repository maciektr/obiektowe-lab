package agh.cs.lab4;

public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);
    private IWorldMap map;

    Animal(IWorldMap map){
        this.map = map;
    }

    Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
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
                MapDirection thisMoveDirection = this.direction;
                if(moveDirection==MoveDirection.BACKWARD)
                    thisMoveDirection = thisMoveDirection.previous().previous();
                Vector2d afterMove = this.position.add(thisMoveDirection.toUnitVector());
                if(this.map.canMoveTo(afterMove))
                    this.position = afterMove;
                break;
            default:
                break;
        }
    }

    /*public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Animal))
            return false;
        Animal that = (Animal) other;
        return this.direction == that.direction && this.position.equals(that.position);
    }*/
}
