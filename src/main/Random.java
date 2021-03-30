package main;

public class Random {
    public enum Generator {LCG, Weyl, Canon};
    private static LCG lcg;
    private static Weyl weyl;
    private static Canon canon;
    
    public static double next(Generator generator) {
        switch (generator) {
            case LCG:
                return lcg.lcg();
            case Weyl:
                return weyl.weyl();
            case Canon:
                return canon.canon();
            default:
                return -1;
        }
    }

    public static void setSeed(long seed) {
        lcg = new LCG(seed);
        weyl = new Weyl(seed);
        canon = new Canon(seed);
    }
}

class LCG {
    private long m = 281474976710655l, a = 0x5deece66dl, c = 0xbl, x;

    LCG(long seed) {
        x = seed;
    }

    public double lcg() {
        x = (x * a + c) % m;
        return x / 4294967295.0;
    }
}

class Weyl {
    private long x = 0, w = 0, s;

    Weyl(long seed) {
        s = seed;
    }

    public double weyl() {
        x *= x;
        x += (w += s);
        x = (x >> 32) | (x << 32);
        return x / 4294967295.0;

    }
}

class Canon {
    private long seed;

    Canon(long seed) {
        this.seed = seed;
    }

    public double canon() {
        Vector2 coord = new Vector2(Math.random(), Math.random());
        return ((seed + Math.sin(coord.dot(new Vector2(12.9898, 78.233)))) * 43758.5453) % 1;
    }
}
