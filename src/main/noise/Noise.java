package main.noise;

import main.util.*;

public abstract class Noise {
    protected int seed;
    protected double frequency;
    protected InterpolationType interpolation;
    private final Vector2[] GRAD2D = {new Vector2(-1, -1), new Vector2(1, -1), new Vector2(-1, 1), new Vector2(1, 1), new Vector2(0, -1), new Vector2(-1, 0), new Vector2(0, 1), new Vector2(1, 0)};
    private final Vector3[] GRAD3D = {new Vector3(1, 1, 0), new Vector3(-1, 1, 0), new Vector3(1, -1, 0), new Vector3(-1, -1, 0), new Vector3(1, 0, 1), new Vector3(-1, 0, 1), new Vector3(1, 0, -1), new Vector3(-1, 0, -1), new Vector3(0, 1, 1), new Vector3(0, -1, 1), new Vector3(0, 1, -1), new Vector3(0, -1, -1), new Vector3(1, 1, 0), new Vector3(0, -1, 1), new Vector3(-1, 1, 0), new Vector3(0, -1, -1)};

    public Noise(int seed) {
        this(seed, 0.01, InterpolationType.Quintic);
    }

    public Noise(int seed, double frequency) {
        this(seed, frequency, InterpolationType.Quintic);
    }

    public Noise(int seed, InterpolationType interpolation) {
        this(seed, 0.01, interpolation);
    }

    public Noise(int seed, double frequency, InterpolationType interpolation) {
        this.seed = seed;
        this.frequency = frequency;
        this.interpolation = interpolation;
    }

    public abstract double getNoise(double x, double y);
    
    public abstract double getNoise(double x, double y, double z);

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

    protected double value3D(int seed, int x, int y, int z) {
        int n = seed;
        n ^= 1619 * x;
        n ^= 31337 * y;
        n ^= 6971 * z;

        return (n * n * n * 60493) / 2147483648.0;
    }

    protected double gradient2D(int seed, int x, int y, double xd, double yd) {
        int hash = seed;
        hash ^= 1619 * x;
        hash ^= 31337 * y;

        hash = hash * hash * hash * 60493;
        hash = (hash >> 13) ^ hash;

        Vector2 g = GRAD2D[hash & 7];

        return xd * g.getX() + yd * g.getY();
    }

    protected double gradient3D(int seed, int x, int y, int z, double xd, double yd, double zd) {
        int hash = seed;
        hash ^= 1619 * x;
        hash ^= 31337 * y;
        hash ^= 6971 * z;

        hash = hash * hash * hash * 60493;
        hash = (hash >> 13) ^ hash;

        Vector3 g = GRAD3D[hash & 15];

        return xd * g.getX() + yd * g.getY() + zd * g.getZ();
    }

    protected int hash2D(int seed, int x, int y) {
        int hash = seed;
        hash ^= 1619 * x;
        hash ^= 31337 * y;

        hash = hash * hash * hash * 60493;
        hash = (hash >> 13) ^ hash;

        return hash;
    }

    protected int hash3D(int seed, int x, int y, int z) {
        int hash = seed;
        hash ^= 1619 * x;
        hash ^= 31337 * y;
        hash ^= 6971 * z;

        hash = hash * hash * hash * 60493;
        hash = (hash >> 13) ^ hash;

        return hash;
    }
}