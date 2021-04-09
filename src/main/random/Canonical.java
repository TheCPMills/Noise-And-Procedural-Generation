package main.random;

import main.util.*;

public class Canonical {
    private int seed;

    public Canonical(int seed) {
        this.seed = seed;
    }

    public double next(double x) {
        return ((Math.sin(seed + x * 12.9898)) * 43758.5453) % 1;
    }
    
    public double next(double x, double y) {
        Vector3 coord = new Vector2(x, y);
        return ((Math.sin(seed + coord.dot(new Vector2(12.9898, 78.233)))) * 43758.5453) % 1;
    }

    public double next(double x, double y, double z) {
        Vector3 coord = new Vector3(x, y, z);
        return ((Math.sin(seed + coord.dot(new Vector3(12.9898, 78.233, 37.719)))) * 43758.5453) % 1;
    }
}