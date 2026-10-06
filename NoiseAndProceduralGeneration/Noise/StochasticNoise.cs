using NoiseAndProceduralGeneration.Random;
using NoiseAndProceduralGeneration.Types;

namespace NoiseAndProceduralGeneration.Noise;

public sealed class StochasticNoise : Noise
{
    public RandomNumberGeneratorParadigm RandomNumberGeneratorParadigm { get; init; } = RandomNumberGeneratorParadigm.LINEAR_RECURRENCE;
    private RandomNumberGenerator RandomNumberGenerator { get; set; } = null!;

    protected override float Get2DNoise(float x, float y)
    {
        float dotProduct = (x * 12.9898f) + (y * 78.233f);
        return Fract(MathF.Sin(Seed + dotProduct) * 43758.5453f);
    }

    protected override float Get3DNoise(float x, float y, float z)
    {
        float dotProduct = (x * 12.9898f) + (y * 78.233f) + (z * 37.719f);
        return Fract(MathF.Sin(Seed + dotProduct) * 43758.5453f);
    }

    private static float Fract(float x)
    {
        return x - MathF.Floor(x);
    }
}