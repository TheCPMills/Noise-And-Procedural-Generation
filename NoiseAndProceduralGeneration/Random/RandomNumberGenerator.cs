namespace NoiseAndProceduralGeneration.Random;

public abstract class RandomNumberGenerator(ulong seed)
{
    public ulong Seed { get; init; } = seed;
    private bool HasNextGaussianComputed { get; set; } = false;
    private float NextComputedGaussian { get; set; }

    public abstract float Next(); // generates psuedo-random number in the normalized unit interval, [0, 1)

    public float Next(float upperBound)
    {
        return Next(0, upperBound);
    }

    public float Next(float lowerBound, float upperBound)
    {
        return lowerBound + Next() * (upperBound - lowerBound);
    }

    public float NextBipolarUnitNormalized() // generates psuedo-random number in the bipolar unit normalized interval, [-1, 1)
    {
        return 2.0f * Next() - 1.0f;
    }

    public float NextGaussian()
    {
        if (HasNextGaussianComputed)
        {
            HasNextGaussianComputed = false;
            return NextComputedGaussian;
        }
        else
        {
            float v1 = Next();
            float v2 = Next();

            if (v1 <= 0f)
            {
                v1 = float.Epsilon;
            }

            NextComputedGaussian = MathF.Sqrt(-2.0f * MathF.Log(v1)) * MathF.Sin(2.0f * MathF.PI * v2);
            HasNextGaussianComputed = true;
            return MathF.Sqrt(-2.0f * MathF.Log(v1)) * MathF.Cos(2.0f * MathF.PI * v2);
        }
    }

    public float NextGaussian(float mean, float standardDeviation)
    {
        return mean + NextGaussian() * standardDeviation;
    }
}