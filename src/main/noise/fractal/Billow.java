package main.noise.fractal;

public class Billow extends Fractal {

    public Billow(int seed) {
        super(seed);
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
}
