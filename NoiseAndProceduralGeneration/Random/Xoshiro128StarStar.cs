namespace NoiseAndProceduralGeneration.Random;

public sealed class Xoshiro128StarStar : RandomNumberGenerator
{
    private uint State0, State1, State2, State3;

    public Xoshiro128StarStar(ulong seed) : base(seed)
    {
        uint u = (uint)seed;
        State0 = Mix(u);
        State1 = Mix(u + 0x9E3779B9U);
        State2 = Mix(u + 0x3C6EF372U);
        State3 = Mix(u + 0xDAA66D2BU);

        if (State0 == 0 && State1 == 0 && State2 == 0 && State3 == 0)
        {
            State0 = 1;
        }
    }

    public override float Next()
    {
        uint z = RotL(State1 * 5U, 7) * 9U;
        uint t = State1 << 9;
        State2 ^= State0;
        State3 ^= State1;
        State1 ^= State2;
        State0 ^= State3;
        State2 ^= t;
        State3 = RotL(State3, 11);
        return z / ((float)uint.MaxValue + 1);
    }
    
    private static uint Mix(uint z)
    {
        z += 0x9E3779B9U;
        z ^= z >> 16;
        z *= 0x85EBCA6BU;
        z ^= z >> 13;
        z *= 0xC2B2AE35U;
        z ^= z >> 16;
        return z;
    }
    private static uint RotL(uint x, int k) => (x << k) | (x >> (32 - k));
}