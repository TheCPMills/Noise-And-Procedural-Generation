package main.random;

public class Weyl {
    private long x = 0, w = 0, seed = 0xb5ad4eceda1ce2a9l;

    public Weyl() {
    }

    public double next() {
        x *= x;
        x += (w += seed);
        x = (x >> 32) | (x << 32);
        return x / 4294967296.0;

    }
}
