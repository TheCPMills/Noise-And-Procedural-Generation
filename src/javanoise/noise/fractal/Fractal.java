package javanoise.noise.fractal;

public class Fractal {
    protected int octaves;
    protected double lacunarity;
    protected double persistence;
    protected float weightedStrength;
    protected double fractalBounding;
    protected FractalType fractalType;

    protected Fractal(int octaves) {
        this(octaves, 2.0, 0.5, 0.0f);
    }

    protected Fractal(int octaves, float weightedStrength) {
        this(octaves, 2.0, 0.5, weightedStrength);
    }

    protected Fractal(int octaves, double lacunarity, double persistence) {
        this(octaves, lacunarity, persistence, 0.0f);
    }
    
    protected Fractal(int octaves, double lacunarity, double persistence, float weightedStrength) {
        this.octaves = octaves;
        this.lacunarity = lacunarity;
        this.persistence = persistence;
        this.weightedStrength = weightedStrength;
        calculateFractalBounding();
    }

    public int octaves() {
        return octaves;
    }

    public double lacunarity() {
        return lacunarity;
    }

    public double persistence() {
        return persistence;
    }

    public float weightedStrength() {
        return weightedStrength;
    }

    public double fractalBounding() {
        return fractalBounding;
    }

    public FractalType fractalType() {
        return fractalType;
    }

    protected void calculateFractalBounding() {
        double gain = Math.abs(persistence);
        double amplitude = gain;
        double amplitudeFractal = 1.0;
        for (int i = 1; i < octaves; i++) {
            amplitudeFractal += amplitude;
            amplitude *= gain;
        }
        fractalBounding = 1 / amplitudeFractal;
    }
}