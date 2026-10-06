namespace NoiseAndProceduralGeneration.Random;

public class MiddleSquareWeylSequence32(ulong seed) : RandomNumberGenerator(seed)
{
    private ulong State = seed;
    private ulong Counter = seed;
    private const ulong WEYL_CONSTANT = 0xb5ad4eceda1ce2a9UL;

    public override float Next()
    {
        State *= State;
        Counter += WEYL_CONSTANT;
        State += Counter;
        State = (State >> 32) | (State << 32);
        return State / ((float)uint.MaxValue + 1);
    }
}