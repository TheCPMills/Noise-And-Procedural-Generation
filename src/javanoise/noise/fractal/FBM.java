package javanoise.noise.fractal;

public class FBM extends Fractal {
    public FBM(int octaves) {
        super(octaves);
        this.fractalType = FractalType.FBM;
    }

    public FBM(int octaves, float weightedStrength) {
        super(octaves, weightedStrength);
        this.fractalType = FractalType.FBM;
    }

    public FBM(int octaves, double lacunarity, double persistence) {
        super(octaves, lacunarity, persistence);
        this.fractalType = FractalType.FBM;
    }

    public FBM(int octaves, double lacunarity, double persistence, float weightedStrength) {
        super(octaves, lacunarity, persistence, weightedStrength);
        this.fractalType = FractalType.FBM;
    }
}