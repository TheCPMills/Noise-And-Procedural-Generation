# JavaNoise API 2.0.0

## Features

- ### Five Noise Functions
  - Guassian
  - Value
  - Cellular
  - Perlin
  - Simplex

- ### Four Fractal Modifiers
  - Fractal Brownian Motion (FBM)
  - Rigid-Multifractal Noise
  - Billow
  - Ping-Pong

- ### Three Random Number Generators
  - Linear Congruential Generator (LCG)
  - XOR Shift
  - Counter-Based Middle Square Sequence (CBSquares)

- ### A Noisemap Generator

- ### A Shuffler / Data Randomizer

## Usage
```java
import javanoise.noise.*;
import javanoise.noise.fractal.*;

public class App {
  public static void main(String[] args) throws Exception {
    int seed = 1337; // seed
    double frequency = 0.01; // noise frequency
    Fractal fractalBase = new FBM(5, 2.00, 0.5, 0.0f); // fractal modifier
    Noise noise = new Perlin(seed, frequency, InterpolationType.HERMITE, fractalBase); // noise function

    NoiseMapGenerator.generateMap(noise, 256, 256, "Noisemap"); // generate noisemap
  }
}
```
