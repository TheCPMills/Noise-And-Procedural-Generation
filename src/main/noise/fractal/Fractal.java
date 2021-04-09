package main.noise.fractal;

import main.noise.*;

public abstract class Fractal extends Noise {
    protected int octaves = 3;
    protected double lacunarity = 2.0;
    protected double gain = 0.5;
    protected double fractalBounding;

    public Fractal(int seed) {
        super(seed);
        calculateFractalBounding();
    }

    public abstract double getNoise(double x, double y);
    
    private void calculateFractalBounding() {
        double amp = gain;
        double ampFractal = 1;
        for (int i = 1; i < octaves; i++) {
            ampFractal += amp;
            amp *= gain;
        }
        fractalBounding = 1 / ampFractal;
    }

    protected double cubic(int seed, double x, double y) {
        x *= frequency;
        y *= frequency;
        
        int x1 = (x >= 0 ? (int) x : (int) x - 1);
        int y1 = (y >= 0 ? (int) y : (int) y - 1);

        int x0 = x1 - 1;
        int y0 = y1 - 1;
        int x2 = x1 + 1;
        int y2 = y1 + 1;
        int x3 = x1 + 2;
        int y3 = y1 + 2;

        double xs = x - x1;
        double ys = y - y1;

        return cubicLerp(cubicLerp(value2D(seed, x0, y0), value2D(seed, x1, y0), value2D(seed, x2, y0), value2D(seed, x3, y0), xs),
                         cubicLerp(value2D(seed, x0, y1), value2D(seed, x1, y1), value2D(seed, x2, y1), value2D(seed, x3, y1), xs),
                         cubicLerp(value2D(seed, x0, y2), value2D(seed, x1, y2), value2D(seed, x2, y2), value2D(seed, x3, y2), xs),
                         cubicLerp(value2D(seed, x0, y3), value2D(seed, x1, y3), value2D(seed, x2, y3), value2D(seed, x3, y3), xs),
                         ys) * (1 / (1.5 * 1.5));
    }
    
    protected double cubicLerp(double a, double b, double c, double d, double t) {
        double p = (d - c) - (a - b);
        return t * t * t * p + t * t * ((a - b) - p) + t * (c - a) + b;
    }
}
