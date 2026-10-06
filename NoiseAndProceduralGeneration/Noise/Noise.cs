namespace NoiseAndProceduralGeneration.Noise;

public abstract class Noise
{
    public required int Seed { get; init; }
    public float Frequency { get; init; } = 0.01f;
    public FractalModifier? FractalModifier { get; init; } = null;

    protected const int PrimeX = 0x1DDE90C9;
    protected const int PrimeY = 0x43C42E4D;
    protected const int PrimeZ = 0x668B6E2F;

    protected abstract float Get2DNoise(float x, float y);
    protected abstract float Get3DNoise(float x, float y, float z);

    public float GetNoise(float x, float y)
    {
        x *= Frequency;
        y *= Frequency;

        if (FractalModifier is not null)
        {
            float sum = 0.0f;
            float amplitude = FractalModifier.FractalBounding;

            for (int i = 0; i < FractalModifier.Octaves; i++)
            {
                float sample = FractalModifier.ApplyFractalModifier2D(Get2DNoise, x, y);
                sum += sample * amplitude;

                x *= FractalModifier.Lacunarity;
                y *= FractalModifier.Lacunarity;
                amplitude *= FractalModifier.Persistence;
            }
            return sum;
        }
        return Get2DNoise(x, y);
    }

    public float GetNoise(float x, float y, float z)
    {
        x *= Frequency;
        y *= Frequency;
        z *= Frequency;

        if (FractalModifier is not null)
        {
            float sum = 0.0f;
            float amplitude = FractalModifier.FractalBounding;

            for (int i = 0; i < FractalModifier.Octaves; i++)
            {
                float sample = FractalModifier.ApplyFractalModifier3D(Get3DNoise, x, y, z);
                sum += sample * amplitude;

                x *= FractalModifier.Lacunarity;
                y *= FractalModifier.Lacunarity;
                z *= FractalModifier.Lacunarity;
                amplitude *= FractalModifier.Persistence;
            }
            return sum;
        }
        return Get3DNoise(x, y, z);
    }

    #region Generate Values
    public float[,] GenerateValues(int width, int height)
    {
        float[,] values = new float[width, height];
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                float value = GetNoise(x, y);
                values[x, y] = value;
            }
        }
        return values;
    }

    public bool[,] GenerateValues(float tolerance, bool isUpperBound, int width, int height)
    {
        return (isUpperBound) ? GenerateValues(-1.0f, tolerance, width, height) : GenerateValues(tolerance, 1.0f, width, height);
    }

    public bool[,] GenerateValues(float lowTolerance, float highTolerance, int width, int height)
    {
        bool[,] values = new bool[width, height];
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                float value = GetNoise(x, y);
                values[x, y] = value <= highTolerance && value >= lowTolerance;
            }
        }
        return values;
    }

    public int[,] GenerateValues(int levels, int width, int height)
    {
        int[,] values = new int[width, height];
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                float value = GetNoise(x, y);
                int level = (int)(levels * (value + 1) / 2);
                if (level > levels - 1)
                {
                    level = levels - 1;
                }
                values[x, y] = level;
            }
        }
        return values;
    }
    #endregion

    #region Helper Methods
    protected static int Hash2D(int seed, int xPrimed, int yPrimed)
    {
        int hash = seed ^ xPrimed ^ yPrimed;
        hash *= 0x27d4eb2d;
        return hash;
    }

    protected static int Hash3D(int seed, int xPrimed, int yPrimed, int zPrimed)
    {
        int hash = seed ^ xPrimed ^ yPrimed ^ zPrimed;
        hash *= 0x27d4eb2d;
        return hash;
    }

    protected static float LinearInterpolation(float a, float b, float t)
    {
        return a + t * (b - a);
    }

    protected static float HermiteInterpolation(float t)
    {
        return t * t * t * (t * (t * 6.0f - 15.0f) + 10.0f);
    }

    protected static float TrigonometricInterpolation(float t)
    {
        return MathF.Sin(MathF.PI * t - MathF.PI / 2.0f) + 1.0f / 2.0f;
    }

    protected static float LogisticInterpolation(float t)
    {
        return 1.0f / (1.0f + MathF.Exp(-10.5f * (t - 0.5f)));
    }
    #endregion
}