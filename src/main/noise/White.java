package main.noise;

public class White extends Noise {

    public White(int seed) {
        super(seed);
    }

    public double getNoise(double x, double y) {
        x *= frequency * 1000000;
        y *= frequency * 1000000;

        int n = seed;
        n ^= 1619 * (int) x;
        n ^= 31337 * (int) y;

        return (n * n * n * 60493) / (float) 2147483648.0;
    }
}
