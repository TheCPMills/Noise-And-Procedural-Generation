namespace NoiseAndProceduralGeneration.Random;

public static class Randomizer
{
    public static void Shuffle<T>(this IList<T> list, RandomNumberGenerator rng)
    {
        for (int index = list.Count - 1; index >= 0; index--)
        {
            int swapIndex = (int)Math.Floor(rng.Next(index + 1));
            (list[index], list[swapIndex]) = (list[swapIndex], list[index]);
        }
    }
}