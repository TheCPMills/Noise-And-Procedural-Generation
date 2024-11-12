package javanoise.random;

public class PCG extends RandomNumberGenerator {
    private long multiplier = 6364136223846793005l, increment = 1442695040888963407l;
    private long state;

    public PCG(int seed) {
        super(seed);
        state = seed + increment;
    }

    protected double nextBipolarUnitNormalized() {
        long x = state;
        long count = x >> 59;

        state = x * multiplier + increment;
        x ^= x >> 18;
        return rotr32((int) (x >> 27), count) / 2147483629.0;
    }
    
    private int rotr32(int x, long r) {
        return x >> r | x << (-r & 31);
    }
}