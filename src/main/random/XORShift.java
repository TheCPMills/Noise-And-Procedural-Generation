package main.random;

public class XORShift extends RNG {
    
    public XORShift(int seed) {
        super(seed);
    }
    
    public double next() {
        seed ^= (seed << 21);
        seed ^= (seed >>> 35);
        seed ^= (seed << 4);
        return seed / 4294967296.0;
    }
}
