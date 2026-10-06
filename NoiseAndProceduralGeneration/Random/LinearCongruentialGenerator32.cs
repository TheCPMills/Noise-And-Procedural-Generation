namespace NoiseAndProceduralGeneration.Random;

public sealed class LinearCongruentialGenerator32(uint seed) : RandomNumberGenerator(seed)
{
    private uint State = seed;
    private const uint A = 0x19660DU;
    private const uint C = 0x3C6EF35FU;
    private const uint M = 4294967295U;

    public override float Next()
    {
        State = unchecked((A * State + C) % M);
        return State / ((float)uint.MaxValue + 1);
    }
}