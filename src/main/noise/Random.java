package main.noise;

import main.util.*;

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

    public static double next(double x, double y) {
        return canon.canon(x, y);
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
        Vector3 coord = new Vector2(Math.random(), Math.random());
        return ((Math.sin(seed + coord.dot(new Vector3(12.9898, 78.233, 37.719)))) * 43758.5453) % 1;
    }

    public double canon(double x, double y) {
        Vector3 coord = new Vector2(x, y);
        return ((Math.sin(seed + coord.dot(new Vector3(12.9898, 78.233, 37.719)))) * 43758.5453) % 1;
    }
}
