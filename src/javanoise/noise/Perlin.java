package javanoise.noise;

import javanoise.noise.fractal.*;

public class Perlin extends Noise {
    protected InterpolationType interpolationType;

    public Perlin(int seed) {
        this(seed, InterpolationType.LINEAR);
    }

    public Perlin(int seed, InterpolationType interpolationType) {
        super(seed);
        this.interpolationType = interpolationType;
    }

    public Perlin(int seed, double frequency) {
        this(seed, frequency, InterpolationType.LINEAR);
    }

    public Perlin(int seed, double frequency, InterpolationType interpolationType) {
        super(seed, frequency);
        this.interpolationType = interpolationType;
    }

    public Perlin(int seed, Fractal fractalBase) {
        this(seed, InterpolationType.LINEAR, fractalBase);
    }

    public Perlin(int seed, InterpolationType interpolationType, Fractal fractalBase) {
        super(seed, fractalBase);
        this.interpolationType = interpolationType;
    }

    public Perlin(int seed, double frequency, Fractal fractalBase) {
        this(seed, frequency, InterpolationType.LINEAR, fractalBase);
    }

    public Perlin(int seed, double frequency, InterpolationType interpolationType, Fractal fractalBase) {
        super(seed, frequency, fractalBase);
        this.interpolationType = interpolationType;
    }

    public double get2DNoise(double x, double y) {
        int x0 = (x >= 0) ? (int) x : (int) x - 1;
        int y0 = (y >= 0) ? (int) y : (int) y - 1;

        double xd0 = x - x0;
        double yd0 = y - y0;
        double xd1 = xd0 - 1;
        double yd1 = yd0 - 1;

        double xs, ys;
        switch (interpolationType) {
            default:
            case LINEAR:
                xs = xd0;
                ys = yd0;
                break;
            case HERMITE:
                xs = hermiteInterpolation(xd0);
                ys = hermiteInterpolation(yd0);
                break;
            case TRIGONOMETRIC:
                xs = trigonometricInterpolation(xd0);
                ys = trigonometricInterpolation(yd0);
                break;
            case LOGISTIC:
                xs = logisticInterpolation(xd0);
                ys = logisticInterpolation(yd0);
                break;
        }

        x0 *= PrimeX;
        y0 *= PrimeY;
        int x1 = x0 + PrimeX;
        int y1 = y0 + PrimeY;

        double xf0 = linearInterpolation(gradient2D(seed, x0, y0, xd0, yd0), gradient2D(seed, x1, y0, xd1, yd0), xs);
        double xf1 = linearInterpolation(gradient2D(seed, x0, y1, xd0, yd1), gradient2D(seed, x1, y1, xd1, yd1), xs);

        return linearInterpolation(xf0, xf1, ys) * 1.4247691104677813f;
    }

    public double get3DNoise(double x, double y, double z) {
        int x0 = (x >= 0 ? (int) x : (int) x - 1);
        int y0 = (y >= 0 ? (int) y : (int) y - 1);
        int z0 = (z >= 0 ? (int) z : (int) z - 1);

        double xd0 = x - x0;
        double yd0 = y - y0;
        double zd0 = z - z0;
        double xd1 = xd0 - 1;
        double yd1 = yd0 - 1;
        double zd1 = zd0 - 1;

        double xs, ys, zs;
        switch (interpolationType) {
            default:
            case LINEAR:
                xs = xd0;
                ys = yd0;
                zs = zd0;
                break;
            case HERMITE:
                xs = hermiteInterpolation(xd0);
                ys = hermiteInterpolation(yd0);
                zs = hermiteInterpolation(zd0);
                break;
            case TRIGONOMETRIC:
                xs = trigonometricInterpolation(xd0);
                ys = trigonometricInterpolation(yd0);
                zs = trigonometricInterpolation(zd0);
                break;
            case LOGISTIC:
                xs = logisticInterpolation(xd0);
                ys = logisticInterpolation(yd0);
                zs = logisticInterpolation(zd0);
                break;
        }

        x0 *= PrimeX;
        y0 *= PrimeY;
        z0 *= PrimeZ;
        int x1 = x0 + PrimeX;
        int y1 = y0 + PrimeY;
        int z1 = z0 + PrimeZ;

        double xf00 = linearInterpolation(gradient3D(seed, x0, y0, z0, xd0, yd0, zd0), gradient3D(seed, x1, y0, z0, xd1, yd0, zd0), xs);
        double xf10 = linearInterpolation(gradient3D(seed, x0, y1, z0, xd0, yd1, zd0), gradient3D(seed, x1, y1, z0, xd1, yd1, zd0), xs);
        double xf01 = linearInterpolation(gradient3D(seed, x0, y0, z1, xd0, yd0, zd1), gradient3D(seed, x1, y0, z1, xd1, yd0, zd1), xs);
        double xf11 = linearInterpolation(gradient3D(seed, x0, y1, z1, xd0, yd1, zd1), gradient3D(seed, x1, y1, z1, xd1, yd1, zd1), xs);

        double yf0 = linearInterpolation(xf00, xf10, ys);
        double yf1 = linearInterpolation(xf01, xf11, ys);

        return linearInterpolation(yf0, yf1, zs) * 0.964921414852142333984375f;
    }
}
