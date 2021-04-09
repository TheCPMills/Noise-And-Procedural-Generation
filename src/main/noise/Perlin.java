package main.noise;

public class Perlin extends Noise {
    public Perlin(int seed) {
        super(seed);
    }

    public double getNoise(double x, double y) {
        x *= frequency;
        y *= frequency;

        int x0 = (x >= 0 ? (int) x : (int) x - 1);
        int y0 = (y >= 0 ? (int) y : (int) y - 1);
        int x1 = x0 + 1;
        int y1 = y0 + 1;

        double xs, ys;
        switch (interpolation) {
        default:
        case Linear:
            xs = x - x0;
            ys = y - y0;
            break;
        case Hermite:
            xs = hermiteInterpolation(x - x0);
            ys = hermiteInterpolation(y - y0);
            break;
        case Quintic:
            xs = quinticInterpolation(x - x0);
            ys = quinticInterpolation(y - y0);
            break;
        }

        double xd0 = x - x0;
        double yd0 = y - y0;
        double xd1 = xd0 - 1;
        double yd1 = yd0 - 1;

        double xf0 = linearInterpolation(gradient2D(seed, x0, y0, xd0, yd0), gradient2D(seed, x1, y0, xd1, yd0), xs);
        double xf1 = linearInterpolation(gradient2D(seed, x0, y1, xd0, yd1), gradient2D(seed, x1, y1, xd1, yd1), xs);

        return linearInterpolation(xf0, xf1, ys);
    }
    
}
