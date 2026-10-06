using NoiseAndProceduralGeneration.Types;

namespace NoiseAndProceduralGeneration.Noise;

public class FractalModifier
{
    public required FractalModifierType FractalModifierType { get; init; }
    public required int Octaves { get; init; }
    public float Lacunarity { get; init; } = 2.0f;
    public float Persistence { get; init; } = 0.5f;
    public float FractalBounding { get => CalculateFractalBounding(); }

    public float ApplyFractalModifier2D(Func<float, float, float> noiseFunction2D, float x, float y)
    {
        if (FractalModifierType == FractalModifierType.DOMAIN_WARP)
        {
            float warpStrength = Persistence * 0.75f;

            float qx = noiseFunction2D(x + 5.2f, y + 1.3f);
            float qy = noiseFunction2D(x + 8.5f, y + 2.8f);

            x += qx * warpStrength;
            y += qy * warpStrength;
        }

        float noiseValue = noiseFunction2D(x, y);
        return GetModifier(noiseValue);
    }

    public float ApplyFractalModifier3D(Func<float, float, float, float> noiseFunction3D, float x, float y, float z)
    {
        if (FractalModifierType == FractalModifierType.DOMAIN_WARP)
        {
            float warpStrength = Persistence * 0.75f;

            float qx = noiseFunction3D(x + 3.1f, y + 5.2f, z + 1.3f);
            float qy = noiseFunction3D(x + 8.5f, y + 2.8f, z + 4.7f);
            float qz = noiseFunction3D(x + 1.9f, y + 7.3f, z + 6.4f);

            x += qx * warpStrength;
            y += qy * warpStrength;
            z += qz * warpStrength;
        }

        float noiseValue = noiseFunction3D(x, y, z);
        return GetModifier(noiseValue);
    }

    private float CalculateFractalBounding()
    {
        float gain = MathF.Abs(Persistence);
        float amplitude = 1.0f;
        float sum = 0.0f;

        for (int i = 0; i < Octaves; i++)
        {
            sum += amplitude;
            amplitude *= gain;
        }
        return 1.0f / sum;
    }

    private float GetModifier(float noiseValue)
    {
        return FractalModifierType switch
        {
            FractalModifierType.RIDGED_MULTIFRACTAL => (1.0f - MathF.Abs(noiseValue)) * (1.0f - MathF.Abs(noiseValue)),
            FractalModifierType.BILLOW => MathF.Abs(noiseValue) * 2.0f - 1.0f,
            FractalModifierType.TURBULENCE => MathF.Abs(noiseValue),
            _ => noiseValue,
        };
    }
}