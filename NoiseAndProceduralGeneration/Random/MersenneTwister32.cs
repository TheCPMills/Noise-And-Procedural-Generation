namespace NoiseAndProceduralGeneration.Random;

public sealed class MersenneTwister32 : RandomNumberGenerator
{
    private const int N = 624;
    private const int M = 397;
    private const uint MATRIX_A = 0x9908B0DFU;
    private const uint UPPER_MASK = 0x80000000U;
    private const uint LOWER_MASK = 0x7FFFFFFFU;

    private readonly uint[] mt = new uint[N];
    private int index = N;

    public MersenneTwister32(uint seed) : base(seed)
    {
        mt[0] = seed;
        for (int i = 1; i < N; i++)
        {
            mt[i] = 0x6C078965U * (mt[i - 1] ^ (mt[i - 1] >> 30)) + (uint)i;
        }
    }

    public override float Next()
    {
        if (index >= N)
        {
            Twist();
        }

        uint y = mt[index++];
        y ^= (y >> 11);
        y ^= (y << 7) & 0x9D2C5680U;
        y ^= (y << 15) & 0xEFC60000U;
        y ^= (y >> 18);
        return y / ((float)uint.MaxValue + 1);
    }

    private void Twist()
    {
        for (int i = 0; i < N; i++)
        {
            uint y = (mt[i] & UPPER_MASK) | (mt[(i + 1) % N] & LOWER_MASK);
            uint next = mt[(i + M) % N] ^ (y >> 1);
            if ((y & 1) != 0)
            {
                next ^= MATRIX_A;
            }
            mt[i] = next;
        }
        index = 0;
    }
}