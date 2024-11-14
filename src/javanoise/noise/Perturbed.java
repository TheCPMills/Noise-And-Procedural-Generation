package javanoise.noise;

import javanoise.noise.fractal.Fractal;

public class Perturbed extends Noise {
    Noise externalNoiseGenerator;
    Noise internalNoiseGenerator;

    public Perturbed(int seed, Noise externalNoiseGenerator, Noise internalNoiseGenerator) {
        super(seed);
        this.externalNoiseGenerator = externalNoiseGenerator;
        this.internalNoiseGenerator = internalNoiseGenerator;
    }

    public Perturbed(int seed, double frequency, Noise externalNoiseGenerator, Noise internalNoiseGenerator) {
        super(seed, frequency);
        this.externalNoiseGenerator = externalNoiseGenerator;
        this.internalNoiseGenerator = internalNoiseGenerator;
    }

    public Perturbed(int seed, Noise externalNoiseGenerator, Noise internalNoiseGenerator, Fractal fractalBase) {
        super(seed, fractalBase);
        this.externalNoiseGenerator = externalNoiseGenerator;
        this.internalNoiseGenerator = internalNoiseGenerator;
    }

    public Perturbed(int seed, double frequency, Noise externalNoiseGenerator, Noise internalNoiseGenerator, Fractal fractalBase) {
        super(seed, frequency, fractalBase);
        this.externalNoiseGenerator = externalNoiseGenerator;
        this.internalNoiseGenerator = internalNoiseGenerator;
    }

    public double get2DNoise(double x, double y) {
        return externalNoiseGenerator.get2DNoise(x + internalNoiseGenerator.get2DNoise(x, y), y + internalNoiseGenerator.get2DNoise(y, x));
    }

    public double get3DNoise(double x, double y, double z) {
        return externalNoiseGenerator.get3DNoise(x + internalNoiseGenerator.get3DNoise(x, y, z), y + internalNoiseGenerator.get3DNoise(y, z, x), z + internalNoiseGenerator.get3DNoise(z, x, y));
    }
}