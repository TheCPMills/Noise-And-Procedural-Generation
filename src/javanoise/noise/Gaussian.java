package javanoise.noise;

import javanoise.noise.fractal.*;
import javanoise.random.*;

public class Gaussian extends Noise {
    private RandomNumberGenerator rng;

    public Gaussian(int seed) {
        this(seed, RNGType.LCG);
    }

    public Gaussian(int seed, RNGType rngType) {
        super(seed);
        setRandomNumberGenerator(rngType);
    }

    public Gaussian(int seed, double frequency) {
        this(seed, frequency, RNGType.LCG);
    }

    public Gaussian(int seed, double frequency, RNGType rngType) {
        super(seed, frequency);
        setRandomNumberGenerator(rngType);
    }


    public Gaussian(int seed, Fractal fractalBase) {
        this(seed, RNGType.LCG, fractalBase);
    }

    public Gaussian(int seed, RNGType rngType, Fractal fractalBase) {
        super(seed, fractalBase);
        setRandomNumberGenerator(rngType);
    }

    public Gaussian(int seed, double frequency, Fractal fractalBase) {
        this(seed, frequency, RNGType.LCG, fractalBase);
    }

    public Gaussian(int seed, double frequency, RNGType rngType, Fractal fractalBase) {
        super(seed, frequency, fractalBase);
        setRandomNumberGenerator(rngType);
    }

    public double get2DNoise(double x, double y) {
        x *= frequency;
        y *= frequency;

        int xi = doubleToInt(x);
        int yi = doubleToInt(y);

        double dotProduct = (x * 12.9898) + (y * 78.233);
        double canonicalValue = (Math.sin(seed + dotProduct) * 43758.5453) % 1;

        double value = value2D(seed, (int) (xi * canonicalValue), (int) (yi * canonicalValue));
        double randomGaussian = rng.nextGaussian(0, 0.25);
        
        return value * randomGaussian;
    }

    public double get3DNoise(double x, double y, double z) {
        x *= frequency;
        y *= frequency;
        z *= frequency;

        int xi = doubleToInt(x);
        int yi = doubleToInt(y);
        int zi = doubleToInt(z);

        double dotProduct = (x * 12.9898) + (y * 78.233) + (z * 37.719);
        double canonicalValue = (Math.sin(seed + dotProduct) * 43758.5453) % 1;

        double value = value3D(seed, (int) (xi * canonicalValue), (int) (yi * canonicalValue), (int) (zi * canonicalValue));
        double randomGaussian = rng.nextGaussian(0, 0.25);

        return value * randomGaussian;
    }

    private int doubleToInt(double d) {
        int i = (int) Double.doubleToRawLongBits(d);
        return i ^ (i >> 16);
    }

    private void setRandomNumberGenerator(RNGType rngType) {
        switch (rngType) {
            default:
            case LCG:
                rng = new LCG(seed);
                break;
            case XORSHIFT:
                rng = new XORShift(seed);
                break;
            case CBSQUARES:
                rng = new CBSquares(seed);
                break;
        }
    }
}
