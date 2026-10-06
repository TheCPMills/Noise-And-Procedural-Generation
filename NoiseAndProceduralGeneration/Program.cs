using NoiseAndProceduralGeneration.Noise;
using NoiseAndProceduralGeneration.Types;

int seed = 1337; // seed
float frequency = 0.01f; // noise frequency
FractalModifier fractalModifier = new() { FractalModifierType = FractalModifierType.FRACTAL_BROWNIAN_MOTION, Octaves = 5 }; // fractal modifier
Noise noise = new GradientNoise { Seed = seed, Frequency = frequency, FractalModifier = fractalModifier }; // noise function
NoiseMapGenerator.GenerateMap(noise, 256, 256, "SampleNoisemap"); // generate noisemap