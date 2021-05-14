package main.noise.fractal;

import main.noise.*;

public abstract class Fractal extends Noise {
    protected int octaves;
    protected double lacunarity;
    protected double gain;
    protected double fractalBounding;

    public Fractal() {
        this((int) System.currentTimeMillis(), 3, 2.0, 0.5);
    }
    
    public Fractal(int seed) {
        this(seed, 3, 2.0, 0.5);
    }

    public Fractal(int seed, double frequency) {
        super(seed, frequency);
        setOctaves(3);
        setLacunarity(2.0);
        setGain(0.5);
        calculateFractalBounding();
    }

    public Fractal(int seed, InterpolationType interpolation) {
        super(seed, interpolation);
        setOctaves(3);
        setLacunarity(2.0);
        setGain(0.5);
        calculateFractalBounding();
    }

    public Fractal(int seed, int octaves) {
        this(seed, octaves, 2.0, 0.5);
    }

    public Fractal(int seed, double lacunarity, double gain) {
        this(seed, 3, lacunarity, gain);
    }

    public Fractal(int seed, double frequency, InterpolationType interpolation) {
        this(seed, frequency, interpolation, 3, 2.0, 0.5);
    }

    public Fractal(int seed, double frequency, int octaves) {
        super(seed, frequency);
        setOctaves(octaves);
        setLacunarity(2.0);
        setGain(0.5);
        calculateFractalBounding();
    }

    public Fractal(int seed, InterpolationType interpolation, int octaves) {
        super(seed, interpolation);
        setOctaves(octaves);
        setLacunarity(2.0);
        setGain(0.5);
        calculateFractalBounding();
    }

    public Fractal(int seed, int octaves, double lacunarity, double gain) {
        super(seed);
        setOctaves(octaves);
        setLacunarity(lacunarity);
        setGain(gain);
        calculateFractalBounding();
    }

    public Fractal(int seed, double frequency, InterpolationType interpolation, int octaves) {
        this(seed, frequency, interpolation, octaves, 2.0, 0.5);
    }

    public Fractal(int seed, double frequency, InterpolationType interpolation, double lacunarity, double gain) {
        this(seed, frequency, interpolation, 3, lacunarity, gain);
    }

    public Fractal(int seed, double frequency, InterpolationType interpolation, int octaves, double lacunarity, double gain) {
        super(seed, frequency, interpolation);
        setOctaves(octaves);
        setLacunarity(lacunarity);
        setGain(gain);
        calculateFractalBounding();
    }

    public abstract double getNoise(double x, double y);

    public abstract double getNoise(double x, double y, double z);
    
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

    protected double cubic(int seed, double x, double y, double z) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        
        int x1 = (x >= 0 ? (int) x : (int) x - 1);
        int y1 = (y >= 0 ? (int) y : (int) y - 1);
        int z1 = (z >= 0 ? (int) z : (int) z - 1);

        int x0 = x1 - 1;
        int y0 = y1 - 1;
        int z0 = z1 - 1;
        int x2 = x1 + 1;
        int y2 = y1 + 1;
        int z2 = z1 + 1;
        int x3 = x1 + 2;
        int y3 = y1 + 2;
        int z3 = z1 + 2;

        double xs = x - x1;
        double ys = y - y1;
        double zs = z - z1;

        return cubicLerp(cubicLerp(cubicLerp(value3D(seed, x0, y0, z0), value3D(seed, x1, y0, z0), value3D(seed, x2, y0, z0), value3D(seed, x3, y0, z0), xs),
                                   cubicLerp(value3D(seed, x0, y1, z0), value3D(seed, x1, y1, z0), value3D(seed, x2, y1, z0), value3D(seed, x3, y1, z0), xs),
                                   cubicLerp(value3D(seed, x0, y2, z0), value3D(seed, x1, y2, z0), value3D(seed, x2, y2, z0), value3D(seed, x3, y2, z0), xs),
                                   cubicLerp(value3D(seed, x0, y3, z0), value3D(seed, x1, y3, z0),value3D(seed, x2, y3, z0), value3D(seed, x3, y3, z0), xs),
                                   ys),
                         cubicLerp(cubicLerp(value3D(seed, x0, y0, z1), value3D(seed, x1, y0, z1), value3D(seed, x2, y0, z1), value3D(seed, x3, y0, z1), xs),
                                   cubicLerp(value3D(seed, x0, y1, z1), value3D(seed, x1, y1, z1), value3D(seed, x2, y1, z1), value3D(seed, x3, y1, z1), xs),
                                   cubicLerp(value3D(seed, x0, y2, z1), value3D(seed, x1, y2, z1), value3D(seed, x2, y2, z1), value3D(seed, x3, y2, z1), xs),
                                   cubicLerp(value3D(seed, x0, y3, z1), value3D(seed, x1, y3, z1), value3D(seed, x2, y3, z1), value3D(seed, x3, y3, z1), xs),
                                   ys),
                         cubicLerp(cubicLerp(value3D(seed, x0, y0, z2), value3D(seed, x1, y0, z2), value3D(seed, x2, y0, z2), value3D(seed, x3, y0, z2), xs),
                                   cubicLerp(value3D(seed, x0, y1, z2), value3D(seed, x1, y1, z2), value3D(seed, x2, y1, z2), value3D(seed, x3, y1, z2), xs),
                                   cubicLerp(value3D(seed, x0, y2, z2), value3D(seed, x1, y2, z2), value3D(seed, x2, y2, z2), value3D(seed, x3, y2, z2), xs),
                                   cubicLerp(value3D(seed, x0, y3, z2), value3D(seed, x1, y3, z2), value3D(seed, x2, y3, z2), value3D(seed, x3, y3, z2), xs),
                                   ys),
                         cubicLerp(cubicLerp(value3D(seed, x0, y0, z3), value3D(seed, x1, y0, z3), value3D(seed, x2, y0, z3), value3D(seed, x3, y0, z3), xs),
                                   cubicLerp(value3D(seed, x0, y1, z3), value3D(seed, x1, y1, z3), value3D(seed, x2, y1, z3), value3D(seed, x3, y1, z3), xs),
                                   cubicLerp(value3D(seed, x0, y2, z3), value3D(seed, x1, y2, z3), value3D(seed, x2, y2, z3), value3D(seed, x3, y2, z3), xs),
                                   cubicLerp(value3D(seed, x0, y3, z3), value3D(seed, x1, y3, z3), value3D(seed, x2, y3, z3), value3D(seed, x3, y3, z3), xs),
                                   ys),
                         zs) * (1 / (1.5 * 1.5 * 1.5));
    }
    
    protected double cubicLerp(double a, double b, double c, double d, double t) {
        double p = (d - c) - (a - b);
        return t * t * t * p + t * t * ((a - b) - p) + t * (c - a) + b;
    }

    public void setOctaves(int octaves) {
        this.octaves = octaves;
    }

    public void setLacunarity(double lacunarity) {
        this.lacunarity = lacunarity;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }
}
