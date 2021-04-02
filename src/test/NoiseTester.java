package test;

import main.*;
import main.Random.Generator;

public class NoiseTester {
    public static void main(String[] args) {
        //NoiseMapGenerator.generate(new White(450193589), 512, 512);
        TerrainGenerator.generate("FractalNoise.png", 512, 256, 7, 89);
    }

    private static void testRandom(long seed, Generator generator) {
        Random.setSeed(seed);
        for (int i = 0; i < 10; i++) {
            System.out.println(Random.next(generator));
        }
    }
}