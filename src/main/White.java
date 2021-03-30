package main;

public class White extends Noise {

    public White(long seed) {
        super(seed);
    }

    public double getNoise(double x, double y) {
        Vector2 coord = new Vector2(x, y);
        return ((seed + Math.sin(coord.dot(new Vector2(12.9898, 78.233)))) * 43758.5453) % 1;
    }
}
