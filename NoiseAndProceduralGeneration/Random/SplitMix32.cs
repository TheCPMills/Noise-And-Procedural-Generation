namespace NoiseAndProceduralGeneration.Random;

public sealed class SplitMix32(uint seed) : RandomNumberGenerator(seed)
{
    private uint State = seed;

    public override float Next()
    {
        State += 0x9E3779B9U;
        uint x = State;
        x = (x ^ (x >> 16)) * 0x85EBCA6BU;
        x = (x ^ (x >> 13)) * 0xC2B2AE35U;
        x ^= (x >> 16);
        return x / ((float)uint.MaxValue + 1);
    }
}
