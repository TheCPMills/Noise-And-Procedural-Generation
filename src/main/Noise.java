package main;

public abstract class Noise {
    protected long seed;

    public Noise(long seed) {
        this.seed = seed;
    }

    public abstract double getNoise(double x, double y);
}