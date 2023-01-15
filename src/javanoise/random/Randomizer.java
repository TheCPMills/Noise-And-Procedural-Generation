package javanoise.random;
import java.util.*;

public class Randomizer {
    private static int SHUFFLE_THRESHOLD = 5;

    @SuppressWarnings("unchecked")
    public static <E> void shuffle(List<E> list, RandomNumberGenerator rng) {
        int size = list.size();
        if (size < SHUFFLE_THRESHOLD) {
            for (int i = size; i > 1; i--) {
                list.set(i - 1, list.set((int) rng.next(i), list.get(i - 1)));
            }
        } else {
            Object[] arr = list.toArray();

            for (int i = size; i > 1; i--) {
                swap(arr, i - 1, (int) rng.next(i));
            }

            ListIterator<E> it = list.listIterator();
            for (Object e : arr) {
                it.next();
                it.set((E) e);
            }
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}