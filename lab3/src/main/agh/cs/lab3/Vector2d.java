package agh.cs.lab3;

import org.jetbrains.annotations.NotNull;

public class Vector2d {
    public final int x;
    public final int y;

    Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "("+this.x+","+this.y+")";
    }

    boolean precedes(@NotNull Vector2d other){
        return this.x <= other.x && this.y<=other.y;
    }

    boolean follows(@NotNull Vector2d other){
        return this.x >= other.x && this.y >= other.y;
    }

    Vector2d upperRight(@NotNull Vector2d other){
        return new Vector2d(Math.max(this.x, other.x),Math.max(this.y, other.y));
    }

    Vector2d lowerLeft(@NotNull Vector2d other){
        return new Vector2d(Math.min(this.x, other.x),Math.min(this.y, other.y));
    }

    Vector2d add(@NotNull Vector2d other){
        return new Vector2d(this.x+other.x, this.y+other.y);
    }

    Vector2d substract(@NotNull Vector2d other){
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return this.x == that.x && this.y == that.y;
    }

    Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }
}
