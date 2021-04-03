package main.util;

public class Vector2 extends Vector3 {
    public Vector2(double x, double y) {
        super(x, y, 0);
    }

    public String toString() {
        return "<" + x + ", " + y + ">";
    }
}