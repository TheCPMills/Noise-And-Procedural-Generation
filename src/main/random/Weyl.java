package main.random;

public class Weyl extends RNG {
    private long x = 0, w = 0;

    public Weyl(int seed) {
        super(seed);
    }

    public double next() {
        x *= x;
        x += (w += seed);
        x = (x >> 32) | (x << 32);
        return x / 9223372036854775807.0;

    }
}
