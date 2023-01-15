package javanoise.noise.fractal;

public class Billow extends Fractal {
    public Billow(int octaves) {
        super(octaves);
        this.fractalType = FractalType.BILLOW;
    }

    public Billow(int octaves, float weightedStrength) {
        super(octaves, weightedStrength);
        this.fractalType = FractalType.BILLOW;
    }

    public Billow(int octaves, double lacunarity, double persistence) {
        super(octaves, lacunarity, persistence);
        this.fractalType = FractalType.BILLOW;
    }

    public Billow(int octaves, double lacunarity, double persistence, float weightedStrength) {
        super(octaves, lacunarity, persistence, weightedStrength);
        this.fractalType = FractalType.BILLOW;
    }
}