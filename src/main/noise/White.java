package main.noise;

import main.util.*;

public class White extends Noise {

    public White(long seed) {
        super(seed);
    }

    public double getNoise(double x, double y) {
        Vector3 coord = new Vector2(x, y);
        return ((Math.sin(seed + coord.dot(new Vector3(12.9898, 78.233, 37.719)))) * 43758.5453) % 1;
    }
}
