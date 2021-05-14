package main.random;

public abstract class RNG {
    protected int seed;

    public RNG(int seed) {
        this.seed = seed;
    }

    public abstract double next();

    public double next(double upperBound) {
        return next(0, upperBound);
    }

    public double next(double lowerBound, double upperBound) {
        return lowerBound + ((next() + 1) * (upperBound - lowerBound)) / 2;
    }
}