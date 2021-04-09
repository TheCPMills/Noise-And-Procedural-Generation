package main.noise.fractal;

public class RigidMultiFractal extends Fractal {

    public RigidMultiFractal(int seed) {
        super(seed);
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
}
