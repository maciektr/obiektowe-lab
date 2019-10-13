package agh.cs.lab3;

import org.jetbrains.annotations.NotNull;

public class Position {
    public final int x;
    public final int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "("+this.x+","+this.y+")";
    }

    boolean precedes(@NotNull Position other){
        return this.x <= other.x && this.y<=other.y;
    }

    boolean follows(@NotNull Position other){
        return this.x >= other.x && this.y >= other.y;
    }

    Position upperRight(@NotNull Position other){
        return new Position(Math.max(this.x, other.x),Math.max(this.y, other.y));
    }

    Position lowerLeft(@NotNull Position other){
        return new Position(Math.min(this.x, other.x),Math.min(this.y, other.y));
    }

    Position add(@NotNull Position other){
        return new Position(this.x+other.x, this.y+other.y);
    }

    Position substract(@NotNull Position other){
        return new Position(this.x - other.x, this.y - other.y);
    }

    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Position))
            return false;
        Position that = (Position) other;
        return this.x == that.x && this.y == that.y;
    }

    Position opposite(){
        return new Position(-this.x, -this.y);
    }
}
