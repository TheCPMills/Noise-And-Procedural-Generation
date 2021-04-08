package main.noise;

public class RidgedMultifractal extends Noise {
    // https://github.com/SpongePowered/noise/blob/master/src/main/java/org/spongepowered/noise/module/source/RidgedMulti.java

    // Default lacunarity for the noise::module::RidgedMulti noise module.
    public static final double DEFAULT_RIDGED_LACUNARITY = 2.0;
    // Default number of octaves for the noise::module::RidgedMulti noise module.
    public static final int DEFAULT_RIDGED_OCTAVE_COUNT = 6;
    // Maximum number of octaves for the noise::module::RidgedMulti noise module.
    public static final int RIDGED_MAX_OCTAVE = 30;

    // Frequency multiplier between successive octaves.
    private double lacunarity = DEFAULT_RIDGED_LACUNARITY;
    // Total number of octaves that generate the ridged-multifractal noise.
    private int octaveCount = DEFAULT_RIDGED_OCTAVE_COUNT;
    // Contains the spectral weights for each octave.
    private double[] spectralWeights;

    public RidgedMultifractal(int seed) {
        super(seed);
        calcSpectralWeights();
    }

    public double getNoise(double x, double y) {
        x *= frequency * 2;
        y *= frequency * 2;

        double signal;
        double value = 0.0;
        double weight = 1.0;

        // These parameters should be user-defined; they may be exposed in a
        // future version of libnoise.
        double offset = 1.0;
        double gain = 2.0;

        for (int curOctave = 0; curOctave < octaveCount; curOctave++) {

            // Make sure that these floating-point values have the same range as a 32-
            // bit integer so that we can pass them to the coherent-noise functions.
            double nx, ny;
            nx = makeInt32Range(x);
            ny = makeInt32Range(y);

            // Get the coherent-noise value.
            signal = gradientCoherentNoise(nx, ny) * 2 - 1;

            // Make the ridges.
            signal = Math.abs(signal);
            signal = offset - signal;

            // Square the signal to increase the sharpness of the ridges.
            signal *= signal;

            // The weighting from the previous octave is applied to the signal.
            // Larger values have higher weights, producing sharp points along the
            // ridges.
            signal *= weight;

            // Weight successive contributions by the previous signal.
            weight = signal * gain;
            if (weight > 1.0) {
                weight = 1.0;
            }
            if (weight < 0.0) {
                weight = 0.0;
            }

            // Add the signal to the output value.
            value += (signal * spectralWeights[curOctave]);

            // Go to the next octave.
            x *= lacunarity;
            y *= lacunarity;
        }

        return value / 1.6;
    }

    private void calcSpectralWeights() {
        // This exponent parameter should be user-defined; it may be exposed in a
        // future version of libnoise.
        double h = 1.0;

        double frequency = 1.0;
        spectralWeights = new double[RIDGED_MAX_OCTAVE];
        for (int i = 0; i < RIDGED_MAX_OCTAVE; i++) {
            // Compute weight for each frequency.
            spectralWeights[i] = Math.pow(frequency, -h);
            frequency *= lacunarity;
        }
    }

    private double gradientCoherentNoise(double x, double y) {
        int x0 = (x >= 0 ? (int) x : (int) x - 1);
        int y0 = (y >= 0 ? (int) y : (int) y - 1);
        int x1 = x0 + 1;
        int y1 = y0 + 1;

        double xs, ys;
        xs = InterpHermiteFunc(x - x0);
        ys = InterpHermiteFunc(y - y0);

        double xd0 = x - x0;
        double yd0 = y - y0;
        double xd1 = xd0 - 1;
        double yd1 = yd0 - 1;

        double xf0 = Lerp(GradCoord2D(seed, x0, y0, xd0, yd0), GradCoord2D(seed, x1, y0, xd1, yd0), xs);
        double xf1 = Lerp(GradCoord2D(seed, x0, y1, xd0, yd1), GradCoord2D(seed, x1, y1, xd1, yd1), xs);

        return Lerp(xf0, xf1, ys);
    }
    
    private double makeInt32Range(double n) {
        if (n >= 1073741824.0) {
            return (2.0 * n % 1073741824.0) - 1073741824.0;
        } else if (n <= -1073741824.0) {
            return (2.0 * n % 1073741824.0) + 1073741824.0;
        } else {
            return n;
        }
    }
}