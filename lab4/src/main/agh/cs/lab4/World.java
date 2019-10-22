package agh.cs.lab4;

public class World {
    public static void main(String []args){
//        Position position1 = new Position(1,2);
//        System.out.println(position1);
//        Position position2 = new Position(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));
//        MapDirection a = MapDirection.NORTH;
//        System.out.println(a.toUnitVector());

        Animal zwierze = new Animal();
        System.out.println(zwierze);
//        zwierze.move(MoveDirection.RIGHT);
//        zwierze.move(MoveDirection.FORWARD);
//        zwierze.move(MoveDirection.FORWARD);
//        zwierze.move(MoveDirection.FORWARD);
//        zwierze.move(MoveDirection.BACKWARD);
        OptionsParser parser = new OptionsParser();
        String[] orders = {"r","forward","forward","forward","backward"};
        MoveDirection[] parsedMoves = parser.parse(orders);
        for(MoveDirection move : parsedMoves){
            zwierze.move(move);
        }
        System.out.println(zwierze);
    }
}
