package javanoise.noise.fractal;

public class RigidMulti extends Fractal {
    public RigidMulti(int octaves) {
        super(octaves);
        this.fractalType = FractalType.RIGID_MULTI;
    }

    public RigidMulti(int octaves, float weightedStrength) {
        super(octaves, weightedStrength);
        this.fractalType = FractalType.RIGID_MULTI;
    }

    public RigidMulti(int octaves, double lacunarity, double persistence) {
        super(octaves, lacunarity, persistence);
        this.fractalType = FractalType.RIGID_MULTI;
    }

    public RigidMulti(int octaves, double lacunarity, double persistence, float weightedStrength) {
        super(octaves, lacunarity, persistence, weightedStrength);
        this.fractalType = FractalType.RIGID_MULTI;
    }
}