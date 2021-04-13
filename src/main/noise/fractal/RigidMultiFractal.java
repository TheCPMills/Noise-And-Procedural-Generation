package main.noise.fractal;

import main.noise.*;

public class RigidMultiFractal extends Fractal {

    public RigidMultiFractal(int seed) {
        super(seed);
    }

    public RigidMultiFractal(int seed, double frequency) {
        super(seed, frequency);
    }

    public RigidMultiFractal(int seed, InterpolationType interpolation) {
        super(seed, interpolation);
    }

    public RigidMultiFractal(int seed, int octaves) {
        super(seed, octaves);
    }

    public RigidMultiFractal(int seed, double lacunarity, double gain) {
        super(seed, lacunarity, gain);
    }

    public RigidMultiFractal(int seed, double frequency, InterpolationType interpolation) {
        super(seed, frequency, interpolation);
    }

    public RigidMultiFractal(int seed, double frequency, int octaves) {
        super(seed, frequency, octaves);
    }

    public RigidMultiFractal(int seed, InterpolationType interpolation, int octaves) {
        super(seed, interpolation, octaves);
    }

    public RigidMultiFractal(int seed, int octaves, double lacunarity, double gain) {
        super(seed, octaves, lacunarity, gain);
    }

    public RigidMultiFractal(int seed, double frequency, InterpolationType interpolation, int octaves) {
        super(seed, frequency, interpolation, octaves);
    }

    public RigidMultiFractal(int seed, double frequency, InterpolationType interpolation, double lacunarity, double gain) {
        super(seed, frequency, interpolation, lacunarity, gain);
    }

    public RigidMultiFractal(int seed, double frequency, InterpolationType interpolation, int octaves, double lacunarity, double gain) {
        super(seed, frequency, interpolation, octaves, lacunarity, gain);
    }

    public double getNoise(double x, double y) {
        int seed = this.seed;
        double sum = 1 - Math.abs(cubic(seed, x, y));
        double amp = 1;
        int i = 0;

        while (++i < octaves) {
            x *= lacunarity;
            y *= lacunarity;

            amp *= gain;
            sum -= (1 - Math.abs(cubic(++seed, x, y))) * amp;
        }

        return sum;
    }

    public double getNoise(double x, double y, double z) {
        int seed = this.seed;
        double sum = 1 - Math.abs(cubic(seed, x, y, z));
        double amp = 1;
        int i = 0;

        while (++i < octaves) {
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;

            amp *= gain;
            sum -= (1 - Math.abs(cubic(++seed, x, y, z))) * amp;
        }

        return sum;
    }
}
