package agh.cs.lab4;

public class Rock implements IMapElement{
    private Vector2d position;
    public Rock(Vector2d position){
        this.position=position;
    }
    public Vector2d getPosition(){
        return this.position;
    }
    public String toString(){
        return "R";
    }
}
