package javanoise.random;

public abstract class RandomNumberGenerator {
    protected int seed;
    private boolean haveNextGaussian = false;
    private double nextGaussian; 

    public RandomNumberGenerator(int seed) {
        this.seed = seed;
    }

    protected abstract double nextBipolarUnitNormalized(); // generates psuedo-random number in the bipolar unit normalized interval, [-1, 1)

    public double next() { // generates psuedo-random number in the normalized unit interval, [0, 1)
        return next(0, 1);
    }

    public double next(double upperBound) {
        return next(0, upperBound);
    }

    public double next(double lowerBound, double upperBound) {
        return lowerBound + ((nextBipolarUnitNormalized() + 1) * (upperBound - lowerBound)) / 2;
    }

    public double nextGaussian() {
        if (haveNextGaussian) {
            haveNextGaussian = false;
            return nextGaussian;
        } else {
            double v1, v2, s;
            do {
                v1 = nextBipolarUnitNormalized();
                v2 = nextBipolarUnitNormalized();
                s = v1 * v1 + v2 * v2;
            } while (s >= 1 || s == 0);
            double multiplier = Math.sqrt(-2 * Math.log(s)/s);
            nextGaussian = v2 * multiplier;
            haveNextGaussian = true;
            return v1 * multiplier;
        }
    }

    public double nextGaussian(double mean, double standardDeviation) {
        return mean + nextGaussian() * standardDeviation;
    }
}