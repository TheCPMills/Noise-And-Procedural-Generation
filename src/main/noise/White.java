package main.noise;

public class White extends Noise {

    public White(int seed) {
        super(seed);
    }

    public double getNoise(double x, double y) {
        x *= frequency * 0.01;
        y *= frequency * 0.01;

        int xi = doubleToInt(x);
        int yi = doubleToInt(y);

        return value2D(seed, xi, yi);
    }

    private int doubleToInt(double d) {
        int i = (int) Double.doubleToRawLongBits(d);
        return i ^ (i >> 16);
    }
}
