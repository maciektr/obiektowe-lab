package agh.cs.lab3;

public class World {
    public static void main(String []args){
//        Position position1 = new Position(1,2);
//        System.out.println(position1);
//        Position position2 = new Position(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));
//        MapDirection a = MapDirection.NORTH;
//        System.out.println(a.toUnitVector());

        Car samochod = new Car();
        System.out.println(samochod);
        samochod.move(MoveDirection.RIGHT);
        samochod.move(MoveDirection.FORWARD);
        samochod.move(MoveDirection.FORWARD);
        samochod.move(MoveDirection.FORWARD);
        System.out.println(samochod);
    }
}
