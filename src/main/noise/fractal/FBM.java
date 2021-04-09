package main.noise.fractal;

public class FBM extends Fractal {

    public FBM(int seed) {
        super(seed);
    }

    public double getNoise(double x, double y) {
        int seed = this.seed;
        double sum = cubic(seed, x, y);
        double amp = 1;
        int i = 0;

        while(++i < octaves) {
            x *= lacunarity;
            y *= lacunarity;

            amp *= gain;
            sum += cubic(++seed, x, y) * amp;
        }
        return sum * fractalBounding;
    }
}
