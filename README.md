# Exploration into Noise and Procedural Generation

## Features

- ### Four Families of Procedural Noise Approaches
  - Stochastic Noise (Canonical White Noise)
  - Lattice-Based Value Coherent Noise
  - Lattice-Based Gradient Coherent Noise
  - Point-Based Cellular Coherent Noise

- ### Five Fractal Modifiers
  - Fractal Brownian Motion (FBM)
  - Ridged-Multifractal
  - Billow
  - Turbulence
  - Domain-Warp

- ### Six Paradigms of Random Number Generators
  - **Linear Recurrence**
	- Linear Congruential Generator (LCG)
  - **Permuted Recurrence**
	- Permuted Congruential Generator (PCG)
  - **Midsquare Method**
	- Middle-Square Weyl Sequence
  - **Counter-Based Hashing**
	- SplitMix
  - **Feedback Shift Register**
	- Mersenee Twister (MT)
  - **Scrambled Linear Engine**
	- Xoshiro128**

- ### A Noisemap Generator
  - Default noisemap (Greyscale or Color)
  - Tolerance-Based noisemap (Greyscale only)
  - Level-Based noisemap (Greyscale or Color)

- ### A Shuffler / Data Randomizer

## Usage
```csharp
using NoiseAndProceduralGeneration.Noise;
using NoiseAndProceduralGeneration.Types;

int seed = 1337; // seed
float frequency = 0.01f; // noise frequency
FractalModifier fractalModifier = new() { FractalModifierType = FractalModifierType.FRACTAL_BROWNIAN_MOTION, Octaves = 5 }; // fractal modifier
Noise noise = new GradientNoise { Seed = seed, Frequency = frequency, FractalModifier = fractalModifier }; // noise function
NoiseMapGenerator.GenerateMap(noise, 256, 256, "SampleNoisemap"); // generate noisemap
```
