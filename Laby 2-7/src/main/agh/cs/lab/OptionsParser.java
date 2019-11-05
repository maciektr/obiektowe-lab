package agh.cs.lab;
import java.util.ArrayList;

public class OptionsParser {
    public MoveDirection[] parse(String[] orders){
        ArrayList<MoveDirection> result = new ArrayList<MoveDirection>();
        for(String order : orders){
            MoveDirection next;
            switch (order){
                case "f":
                case "forward":
                    next = MoveDirection.FORWARD;
                    break;
                case "b":
                case "backward":
                    next = MoveDirection.BACKWARD;
                    break;
                case "r":
                case "right":
                    next = MoveDirection.RIGHT;
                    break;
                case "l":
                case "left":
                    next = MoveDirection.LEFT;
                    break;
                default:
                    throw new IllegalArgumentException(order + " is not legal move specification");
            }
            result.add(next);
        }
        return result.toArray(new MoveDirection[0]);
    }
}
