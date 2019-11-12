package agh.cs.lab;

import java.util.Comparator;

public class Vector2dComparatorFirst implements Comparator {
    @Override
    public int compare(Object o, Object t1) {
        if(o == t1)
            return 0;
        if(!(o instanceof Vector2d) || !(t1 instanceof Vector2d))
            return 0;
        Vector2d first = (Vector2d) o;
        Vector2d second = (Vector2d) t1;
        if(first.equals(second))
            return 0;
        if(first.x == second.x)
            return (first.y < second.y ? -1:1);
        return (first.x < second.x ? -1:1);
    }
}
