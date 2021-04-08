package test;

import main.generators.*;
import main.noise.*;
import java.io.*;

public class NoiseTester {
    public static void main(String[] args) throws IOException {
        NoiseMapGenerator.generate(new Fractal(425735091, 9), 512, 512);
        TerrainGenerator.generate("assets/NoiseMaps/FractalNoise.png", 512, 256, 7, 89);
    }
}