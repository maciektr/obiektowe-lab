package agh.cs.lab4;

public class World {
    public static void main(String []args){
        /*Position position1 = new Position(1,2);
        System.out.println(position1);
        Position position2 = new Position(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        MapDirection a = MapDirection.NORTH;
        System.out.println(a.toUnitVector());*/

        /*Animal zwierze = new Animal();
        System.out.println(zwierze);
        OptionsParser parser = new OptionsParser();
        String[] orders = {"r","forward","forward","forward","backward"};
        MoveDirection[] parsedMoves = parser.parse(orders);
        for(MoveDirection move : parsedMoves){
            zwierze.move(move);
        }
        System.out.println(zwierze);*/

        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        map.place(new Animal(map));
        map.place(new Animal(map,new Vector2d(3,4)));
        map.run(directions);


    }
}
