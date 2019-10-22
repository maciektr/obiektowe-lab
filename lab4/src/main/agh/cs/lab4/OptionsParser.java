package agh.cs.lab4;
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
                default:
                case "l":
                case "left":
                    next = MoveDirection.LEFT;
                    break;
            }
            result.add(next);
        }
        return result.toArray(new MoveDirection[0]);
    }
}
