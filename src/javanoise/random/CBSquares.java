package javanoise.random;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CBSquares extends RandomNumberGenerator {
    private long counter = 0xffffffffffffffffl;
    private long key;

    public CBSquares(int seed) {
        super(seed);

        String line;
        try (Stream<String> lines = Files.lines(Paths.get("src/javanoise/random/keys.txt"))) {
            line = lines.skip(seed % 25000 + 1).findFirst().get();
            key = Long.parseUnsignedLong(line.substring(2), 16);
        } catch (IOException e) {
            key = 0xffffffffffffffffl;
        }
    }

    public double next() {
        long x = counter++ * key;
        long y = x;
        long z = y + key;

        x = x * x + y;
        x = (x >> 32) | (x << 32); /* round 1 */

        x = x * x + z;
        x = (x >> 32) | (x << 32); /* round 2 */

        x = x * x + y;
        x = (x >> 32) | (x << 32); /* round 3 */

        x = (x * x + z) >> 32; /* round 4 */
        return x / 4294967296.0;
    }
}
