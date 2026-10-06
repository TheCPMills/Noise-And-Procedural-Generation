namespace NoiseAndProceduralGeneration.Random;

public sealed class PermutedCongruentialGenerator32(uint seed) : RandomNumberGenerator(seed)
{
    private ulong State = seed + INCREMENT;
    private const ulong MULTIPLIER = 0x5851F42D4C957F2DUL;
    private const ulong INCREMENT = 0x14057B7EF767814FUL;

    public override float Next()
    {
        ulong oldState = State;
        State = unchecked(MULTIPLIER * oldState + INCREMENT);

        uint xorshift = (uint)((oldState >> 18) ^ oldState) >> 27;
        int rot = (int)(oldState >> 59);

        uint w = RotR(xorshift, rot);
        return w / ((float)uint.MaxValue + 1);
    }
    private static uint RotR(uint x, int r) => (x >> r) | (x << ((32 - r) & 31));
}