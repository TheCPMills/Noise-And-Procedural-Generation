package main.noise;

import main.util.*;

public abstract class Noise {
    protected int seed;
    protected double frequency = 0.01;
    protected InterpolationType interpolation = InterpolationType.Quintic;
    private static final Vector2[] GRAD_2D = {new Vector2(-1, -1), new Vector2(1, -1), new Vector2(-1, 1), new Vector2(1, 1), new Vector2(0, -1), new Vector2(-1, 0), new Vector2(0, 1), new Vector2(1, 0)};

    public Noise(int seed) {
        this.seed = seed;
    }

    public abstract double getNoise(double x, double y);
    
    // public abstract double getNoise(double x, double y, double z);

    protected double linearInterpolation(double a, double b, double t) {
        return a + t * (b - a);
    }
    
    protected double hermiteInterpolation(double t) {
        return t * t * (3 - 2 * t);
    }

    protected double quinticInterpolation(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    protected double value2D(int seed, int x, int y) {
        int n = seed;
        n ^= 1619 * x;
        n ^= 31337 * y;

        return (n * n * n * 60493) / 2147483648.0;
    }

    protected double gradient2D(int seed, int x, int y, double xd, double yd) {
        int hash = seed;
        hash ^= 1619 * x;
        hash ^= 31337 * y;

        hash = hash * hash * hash * 60493;
        hash = (hash >> 13) ^ hash;

        Vector2 g = GRAD_2D[hash & 7];

        return xd * g.getX() + yd * g.getY();
    }

    protected int hash2D(int seed, int x, int y) {
        int hash = seed;
        hash ^= 1619 * x;
        hash ^= 31337 * y;

        hash = hash * hash * hash * 60493;
        hash = (hash >> 13) ^ hash;

        return hash;
    }
}
enum InterpolationType {
    Linear, Hermite, Quintic
}