package main.noise;

public class White extends Noise {

    public White(int seed) {
        super(seed);
    }

    public White(int seed, double frequency) {
        super(seed, frequency);
    }

    public White(int seed, InterpolationType interpolation) {
        super(seed, interpolation);
    }

    public White(int seed, double frequency, InterpolationType interpolation) {
        super(seed, frequency, interpolation);
    }

    public double getNoise(double x, double y) {
        x *= frequency * 0.01;
        y *= frequency * 0.01;

        int xi = doubleToInt(x);
        int yi = doubleToInt(y);

        return value2D(seed, xi, yi);
    }

    public double getNoise(double x, double y, double z) {
        x *= frequency * 0.01;
        y *= frequency * 0.01;
        x *= frequency * 0.01;

        int xi = doubleToInt(x);
        int yi = doubleToInt(y);
        int zi = doubleToInt(z);

        return value3D(seed, xi, yi, zi);
    }

    private int doubleToInt(double d) {
        int i = (int) Double.doubleToRawLongBits(d);
        return i ^ (i >> 16);
    }
}
