package javanoise.noise;

import javanoise.noise.fractal.*;

public class Value extends Noise{
    protected InterpolationType interpolationType;

    public Value(int seed) {
        this(seed, InterpolationType.LINEAR);
    }

    public Value(int seed, InterpolationType interpolationType) {
        super(seed);
        this.interpolationType = interpolationType;
    }

    public Value(int seed, double frequency) {
        this(seed, frequency, InterpolationType.LINEAR);
    }

    public Value(int seed, double frequency, InterpolationType interpolationType) {
        super(seed, frequency);
        this.interpolationType = interpolationType;
    }

    public Value(int seed, Fractal fractalBase) {
        this(seed, InterpolationType.LINEAR, fractalBase);
    }

    public Value(int seed, InterpolationType interpolationType, Fractal fractalBase) {
        super(seed, fractalBase);
        this.interpolationType = interpolationType;
    }

    public Value(int seed, double frequency, Fractal fractalBase) {
        this(seed, frequency, InterpolationType.LINEAR, fractalBase);
    }

    public Value(int seed, double frequency, InterpolationType interpolationType, Fractal fractalBase) {
        super(seed, frequency, fractalBase);
        this.interpolationType = interpolationType;
    }
    
    public double get2DNoise(double x, double y) {
        x *= frequency;
        y *= frequency;

        int x0 = (x >= 0 ? (int) x : (int) x - 1);
        int y0 = (y >= 0 ? (int) y : (int) y - 1);

        double xs, ys;
        switch (interpolationType) {
            default:
            case LINEAR:
                xs = x - x0;
                ys = y - y0;
                break;
            case HERMITE:
                xs = hermiteInterpolation(x - x0);
                ys = hermiteInterpolation(y - y0);
                break;
            case TRIGONOMETRIC:
                xs = trigonometricInterpolation(x - x0);
                ys = trigonometricInterpolation(y - y0);
                break;
            case LOGISTIC:
                xs = logisticInterpolation(x - x0);
                ys = logisticInterpolation(y - y0);
                break;
        }

        x0 *= PrimeX;
        y0 *= PrimeY;
        int x1 = x0 + PrimeX;
        int y1 = y0 + PrimeY;

        double xf0 = linearInterpolation(value2D(seed, x0, y0), value2D(seed, x1, y0), xs);
        double xf1 = linearInterpolation(value2D(seed, x0, y1), value2D(seed, x1, y1), xs);

        return linearInterpolation(xf0, xf1, ys);
    }

    public double get3DNoise(double x, double y, double z) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        
        int x0 = (x >= 0 ? (int) x : (int) x - 1);
        int y0 = (y >= 0 ? (int) y : (int) y - 1);
        int z0 = (z >= 0 ? (int) z : (int) z - 1);

        double xs, ys, zs;
        switch (interpolationType) {
            default:
            case LINEAR:
                xs = x - x0;
                ys = y - y0;
                zs = z - z0;
                break;
            case HERMITE:
                xs = hermiteInterpolation(x - x0);
                ys = hermiteInterpolation(y - y0);
                zs = hermiteInterpolation(z - z0);
                break;
            case TRIGONOMETRIC:
                xs = trigonometricInterpolation(x - x0);
                ys = trigonometricInterpolation(y - y0);
                zs = trigonometricInterpolation(z - z0);
                break;
            case LOGISTIC:
                xs = logisticInterpolation(x - x0);
                ys = logisticInterpolation(y - y0);
                zs = logisticInterpolation(z - z0);
                break;
        }

        x0 *= PrimeX;
        y0 *= PrimeY;
        z0 *= PrimeZ;
        int x1 = x0 + PrimeX;
        int y1 = y0 + PrimeY;
        int z1 = z0 + PrimeZ;

        double xf00 = linearInterpolation(value3D(seed, x0, y0, z0), value3D(seed, x1, y0, z0), xs);
        double xf10 = linearInterpolation(value3D(seed, x0, y1, z0), value3D(seed, x1, y1, z0), xs);
        double xf01 = linearInterpolation(value3D(seed, x0, y0, z1), value3D(seed, x1, y0, z1), xs);
        double xf11 = linearInterpolation(value3D(seed, x0, y1, z1), value3D(seed, x1, y1, z1), xs);

        double yf0 = linearInterpolation(xf00, xf10, ys);
        double yf1 = linearInterpolation(xf01, xf11, ys);

        return linearInterpolation(yf0, yf1, zs);
    }

}
