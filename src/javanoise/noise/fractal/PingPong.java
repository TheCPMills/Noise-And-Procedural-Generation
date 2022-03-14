package javanoise.noise.fractal;

public class PingPong extends Fractal {
    protected double pingPongStrength;

    public PingPong(int octaves) {
        this(octaves, 2.0);
    }

    public PingPong(int octaves, double pingPongStrength) {
        super(octaves);
        this.pingPongStrength = pingPongStrength;
        this.fractalType = FractalType.PING_PONG;
    }

    public PingPong(int octaves, float weightedStrength) {
        this(octaves, 2.0, weightedStrength);
    }

    public PingPong(int octaves, double pingPongStrength, float weightedStrength) {
        super(octaves, weightedStrength);
        this.pingPongStrength = pingPongStrength;
        this.fractalType = FractalType.PING_PONG;
    }

    public PingPong(int octaves, double lacunarity, double persistence) {
        this(octaves, lacunarity, persistence, 2.0);
    }

    public PingPong(int octaves, double lacunarity, double persistence, double pingPongStrength) {
        super(octaves, lacunarity, persistence);
        this.pingPongStrength = pingPongStrength;
        this.fractalType = FractalType.PING_PONG;
    }

    public PingPong(int octaves, double lacunarity, double persistence, float weightedStrength) {
        this(octaves, lacunarity, persistence, 2.0, weightedStrength);
    }

    public PingPong(int octaves, double lacunarity, double persistence, double pingPongStrength, float weightedStrength) {
        super(octaves, lacunarity, persistence, weightedStrength);
        this.pingPongStrength = pingPongStrength;
        this.fractalType = FractalType.PING_PONG;
    }

    public double pingPongStrength() {
        return pingPongStrength;
    }
}
