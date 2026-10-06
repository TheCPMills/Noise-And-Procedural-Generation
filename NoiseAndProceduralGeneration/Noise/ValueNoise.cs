using NoiseAndProceduralGeneration.Types;

namespace NoiseAndProceduralGeneration.Noise;

public sealed class ValueNoise : Noise
{
    public InterpolationType InterpolationType { get; init; } = InterpolationType.LINEAR;

    protected override float Get2DNoise(float x, float y)
    {
        int x0 = (x >= 0) ? (int)x : (int)x - 1;
        int y0 = (y >= 0) ? (int)y : (int)y - 1;

        float xs, ys;
        switch (InterpolationType)
        {
            default:
            case InterpolationType.LINEAR:
                xs = x - x0;
                ys = y - y0;
                break;
            case InterpolationType.HERMITE:
                xs = HermiteInterpolation(x - x0);
                ys = HermiteInterpolation(y - y0);
                break;
            case InterpolationType.TRIGONOMETRIC:
                xs = TrigonometricInterpolation(x - x0);
                ys = TrigonometricInterpolation(y - y0);
                break;
            case InterpolationType.LOGISTIC:
                xs = LogisticInterpolation(x - x0);
                ys = LogisticInterpolation(y - y0);
                break;
        }

        x0 *= PrimeX;
        y0 *= PrimeY;
        int x1 = x0 + PrimeX;
        int y1 = y0 + PrimeY;

        float xf0 = LinearInterpolation(Value2D(Seed, x0, y0), Value2D(Seed, x1, y0), xs);
        float xf1 = LinearInterpolation(Value2D(Seed, x0, y1), Value2D(Seed, x1, y1), xs);

        return LinearInterpolation(xf0, xf1, ys);
    }

    protected override float Get3DNoise(float x, float y, float z)
    {
        int x0 = (x >= 0) ? (int)x : (int)x - 1;
        int y0 = (y >= 0) ? (int)y : (int)y - 1;
        int z0 = (z >= 0) ? (int)z : (int)z - 1;

        float xs, ys, zs;
        switch (InterpolationType)
        {
            default:
            case InterpolationType.LINEAR:
                xs = x - x0;
                ys = y - y0;
                zs = z - z0;
                break;
            case InterpolationType.HERMITE:
                xs = HermiteInterpolation(x - x0);
                ys = HermiteInterpolation(y - y0);
                zs = HermiteInterpolation(z - z0);
                break;
            case InterpolationType.TRIGONOMETRIC:
                xs = TrigonometricInterpolation(x - x0);
                ys = TrigonometricInterpolation(y - y0);
                zs = TrigonometricInterpolation(z - z0);
                break;
            case InterpolationType.LOGISTIC:
                xs = LogisticInterpolation(x - x0);
                ys = LogisticInterpolation(y - y0);
                zs = LogisticInterpolation(z - z0);
                break;
        }

        x0 *= PrimeX;
        y0 *= PrimeY;
        z0 *= PrimeZ;
        int x1 = x0 + PrimeX;
        int y1 = y0 + PrimeY;
        int z1 = z0 + PrimeZ;

        float xf00 = LinearInterpolation(Value3D(Seed, x0, y0, z0), Value3D(Seed, x1, y0, z0), xs);
        float xf10 = LinearInterpolation(Value3D(Seed, x0, y1, z0), Value3D(Seed, x1, y1, z0), xs);
        float xf01 = LinearInterpolation(Value3D(Seed, x0, y0, z1), Value3D(Seed, x1, y0, z1), xs);
        float xf11 = LinearInterpolation(Value3D(Seed, x0, y1, z1), Value3D(Seed, x1, y1, z1), xs);

        float yf0 = LinearInterpolation(xf00, xf10, ys);
        float yf1 = LinearInterpolation(xf01, xf11, ys);

        return LinearInterpolation(yf0, yf1, zs);
    }

    #region Helper Methods
    private static float Value2D(int Seed, int xPrimed, int yPrimed)
    {
        int hash = Hash2D(Seed, xPrimed, yPrimed);

        hash *= hash;
        hash ^= hash << 19;
        return hash / ((float)int.MaxValue + 1);
    }

    private static float Value3D(int Seed, int xPrimed, int yPrimed, int zPrimed)
    {
        int hash = Hash3D(Seed, xPrimed, yPrimed, zPrimed);

        hash *= hash;
        hash ^= hash << 19;
        return hash / ((float)int.MaxValue + 1);
    }
    #endregion
}