package main.noise.fractal;

import main.noise.*;

public class Billow extends Fractal {

    public Billow(int seed) {
        super(seed);
    }

    public Billow(int seed, double frequency) {
        super(seed, frequency);
    }

    public Billow(int seed, InterpolationType interpolation) {
        super(seed, interpolation);
    }

    public Billow(int seed, int octaves) {
        super(seed, octaves);
    }

    public Billow(int seed, double lacunarity, double gain) {
        super(seed, lacunarity, gain);
    }

    public Billow(int seed, double frequency, InterpolationType interpolation) {
        super(seed, frequency, interpolation);
    }

    public Billow(int seed, double frequency, int octaves) {
        super(seed, frequency, octaves);
    }

    public Billow(int seed, InterpolationType interpolation, int octaves) {
        super(seed, interpolation, octaves);
    }

    public Billow(int seed, int octaves, double lacunarity, double gain) {
        super(seed, octaves, lacunarity, gain);
    }

    public Billow(int seed, double frequency, InterpolationType interpolation, int octaves) {
        super(seed, frequency, interpolation, octaves);
    }

    public Billow(int seed, double frequency, InterpolationType interpolation, double lacunarity, double gain) {
        super(seed, frequency, interpolation, lacunarity, gain);
    }

    public Billow(int seed, double frequency, InterpolationType interpolation, int octaves, double lacunarity, double gain) {
        super(seed, frequency, interpolation, octaves, lacunarity, gain);
    }

    public double getNoise(double x, double y) {
        int seed = this.seed;
        double sum = Math.abs(cubic(seed, x, y)) * 2 - 1;
        double amp = 1;
        int i = 0;

        while (++i < octaves) {
            x *= lacunarity;
            y *= lacunarity;

            amp *= gain;
            sum += (Math.abs(cubic(++seed, x, y)) * 2 - 1) * amp;
        }
        return sum * fractalBounding;
    }

    public double getNoise(double x, double y, double z) {
        int seed = this.seed;
        double sum = Math.abs(cubic(seed, x, y, z)) * 2 - 1;
        double amp = 1;
        int i = 0;

        while (++i < octaves) {
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;

            amp *= gain;
            sum += (Math.abs(cubic(++seed, x, y, z)) * 2 - 1) * amp;
        }

        return sum * fractalBounding;
    }
}
