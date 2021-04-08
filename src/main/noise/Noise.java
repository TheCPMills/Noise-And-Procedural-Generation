package main.noise;

import main.util.*;

public abstract class Noise {
    protected int seed;
    protected double frequency = 0.01;
    private static final Vector2[] GRAD_2D = {new Vector2(-1, -1), new Vector2(1, -1), new Vector2(-1, 1), new Vector2(1, 1), new Vector2(0, -1), new Vector2(-1, 0), new Vector2(0, 1), new Vector2(1, 0)};
    public enum Interp {Linear, Hermite, Quintic}
    protected Interp interpolation = Interp.Quintic;

    public Noise(int seed) {
        this.seed = seed;
    }

    public abstract double getNoise(double x, double y);

    protected double InterpHermiteFunc(double t) {
        return t * t * (3 - 2 * t);
    }

    protected double InterpQuinticFunc(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    protected double GradCoord2D(int seed, int x, int y, double xd, double yd) {
        int hash = seed;
        hash ^= 1619 * x;
        hash ^= 31337 * y;

        hash = hash * hash * hash * 60493;
        hash = (hash >> 13) ^ hash;

        Vector2 g = GRAD_2D[hash & 7];

        return xd * g.getX() + yd * g.getY();
    }

    protected double Lerp(double a, double b, double t) {
        return a + t * (b - a);
    }
}