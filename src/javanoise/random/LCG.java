package javanoise.random;

public class LCG extends RandomNumberGenerator {
    private long m = 2147483629l, a = 0x5851f42d4c957f2dl, c = 0x14057b7ef767814fl;

    public LCG(int seed) {
        super(seed);
    }

    public double next() {
        seed = (int) ((seed * a + c) % m);
        return seed / 2147483629.0;
    }
}
