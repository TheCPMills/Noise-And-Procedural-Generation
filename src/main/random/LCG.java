package main.random;

public class LCG extends RNG {
    private long m = 281474976710655l, a = 0x5deece66dl, c = 0xbl;

    public LCG(int seed) {
        super(seed);
    }

    public double next() {
        seed = (int) ((seed * a + c) % m);
        return seed / 4294967295.0;
    }
}
