package javanoise.random;
import java.util.*;

public class Randomizer {
    public static <E> void shuffle(List<E> list, RandomNumberGenerator rng) {
        for (int index = list.size() - 1; index >= 0; index--) {
            int swapIndex = (int) Math.floor((int) rng.next(index + 1));
            list.set(index, list.set(swapIndex, list.get(index)));
        }
    }
}