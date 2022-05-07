
import javanoise.noise.*;
import javanoise.noise.fractal.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        int seed = 1337;
        double frequency = 0.01;
        Fractal fractalBase = new FBM(5, 2.00, 0.5, 0.0f);
        Noise noise = new Perlin(seed, frequency, InterpolationType.HERMITE, fractalBase);

        NoiseMapGenerator.generateMap(noise, 256, 256, "Noisemap");
    }
}
