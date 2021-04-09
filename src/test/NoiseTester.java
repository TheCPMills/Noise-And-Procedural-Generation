package test;

import main.generators.*;
import main.noise.*;
import main.noise.fractal.*;

import java.io.*;

public class NoiseTester {
    public static void main(String[] args) throws IOException {
        generateNoiseMap(NoiseType.Simplex, 425735091);
        generate2DTerrain(NoiseType.Simplex);
        generate25DTerrain(NoiseType.Simplex);
    }

    private static void generateNoiseMap(NoiseType noiseType, int seed) {
        Noise noise;
        switch (noiseType) {
            case White:
                noise = new White(seed);
                break;
            case Cellular:
                noise = new Cellular(seed);
                break;
            case Value:
                noise = new Value(seed);
                break;
            case Perlin:
                noise = new Perlin(seed);
                break;
            case Simplex:
                noise = new Simplex(seed);
                break;
            case FBM:
                noise = new FBM(seed);
                break;
            case Billow:
                noise = new Billow(seed);
                break;
            case RigidMultiFractal:
                noise = new RigidMultiFractal(seed);
                break;
            default:
                noise = null;
        }
        NoiseMapGenerator.generate(noise, 512, 512);
    }

    private static void generate2DTerrain(NoiseType noiseType) {
        TerrainGenerator2D.generate(noiseType.name(), 512, 256, 7, 89);
    }

    private static void generate25DTerrain(NoiseType noiseType) {
        TerrainGenerator25D.generate(noiseType.name(), 100);
    }

    private static void generate3DTerrain(NoiseType noiseType, int seed) {
        Noise noise;
        switch (noiseType) {
            case White:
                noise = new White(seed);
                break;
            case Cellular:
                noise = new Cellular(seed);
                break;
            case Value:
                noise = new Value(seed);
                break;
            case Perlin:
                noise = new Perlin(seed);
                break;
            case Simplex:
                noise = new Simplex(seed);
                break;
            case FBM:
                noise = new FBM(seed);
                break;
            case Billow:
                noise = new Billow(seed);
                break;
            case RigidMultiFractal:
                noise = new RigidMultiFractal(seed);
                break;
            default:
                noise = null;
        }
        TerrainGenerator3D.generate(noise, 100);
    }

    private enum NoiseType {
        White, Cellular, Value, Perlin, Simplex, FBM, Billow, RigidMultiFractal;
    }
}