package javanoise.noise;

import javanoise.noise.fractal.*;

public abstract class Noise {
    protected int seed;
    protected double frequency;

    protected InterpolationType interpolationType;
    protected Fractal fractalBase;

    protected final int PrimeX = 501125321;
    protected final int PrimeY = 1136930381;
    protected final int PrimeZ = 1720413743;

private final double[] GRAD2D = {0.130526192220052,  0.99144486137381,   0.38268343236509,   0.923879532511287,  0.608761429008721,  0.793353340291235,  0.793353340291235,  0.608761429008721,
         0.923879532511287,  0.38268343236509,   0.99144486137381,   0.130526192220051,  0.99144486137381,  -0.130526192220051,  0.923879532511287, -0.38268343236509,
         0.793353340291235, -0.60876142900872,   0.608761429008721, -0.793353340291235,  0.38268343236509,  -0.923879532511287,  0.130526192220052, -0.99144486137381,
        -0.130526192220052, -0.99144486137381,  -0.38268343236509,  -0.923879532511287, -0.608761429008721, -0.793353340291235, -0.793353340291235, -0.608761429008721,
        -0.923879532511287, -0.38268343236509,  -0.99144486137381,  -0.130526192220052, -0.99144486137381,   0.130526192220051, -0.923879532511287,  0.38268343236509,
        -0.793353340291235,  0.608761429008721, -0.608761429008721,  0.793353340291235, -0.38268343236509,   0.923879532511287, -0.130526192220052,  0.99144486137381,
         0.130526192220052,  0.99144486137381,   0.38268343236509,   0.923879532511287,  0.608761429008721,  0.793353340291235,  0.793353340291235,  0.608761429008721,
         0.923879532511287,  0.38268343236509,   0.99144486137381,   0.130526192220051,  0.99144486137381,  -0.130526192220051,  0.923879532511287, -0.38268343236509,
         0.793353340291235, -0.60876142900872,   0.608761429008721, -0.793353340291235,  0.38268343236509,  -0.923879532511287,  0.130526192220052, -0.99144486137381,
        -0.130526192220052, -0.99144486137381,  -0.38268343236509,  -0.923879532511287, -0.608761429008721, -0.793353340291235, -0.793353340291235, -0.608761429008721,
        -0.923879532511287, -0.38268343236509,  -0.99144486137381,  -0.130526192220052, -0.99144486137381,   0.130526192220051, -0.923879532511287,  0.38268343236509,
        -0.793353340291235,  0.608761429008721, -0.608761429008721,  0.793353340291235, -0.38268343236509,   0.923879532511287, -0.130526192220052,  0.99144486137381,
         0.130526192220052,  0.99144486137381,   0.38268343236509,   0.923879532511287,  0.608761429008721,  0.793353340291235,  0.793353340291235,  0.608761429008721,
         0.923879532511287,  0.38268343236509,   0.99144486137381,   0.130526192220051,  0.99144486137381,  -0.130526192220051,  0.923879532511287, -0.38268343236509,
         0.793353340291235, -0.60876142900872,   0.608761429008721, -0.793353340291235,  0.38268343236509,  -0.923879532511287,  0.130526192220052, -0.99144486137381,
        -0.130526192220052, -0.99144486137381,  -0.38268343236509,  -0.923879532511287, -0.608761429008721, -0.793353340291235, -0.793353340291235, -0.608761429008721,
        -0.923879532511287, -0.38268343236509,  -0.99144486137381,  -0.130526192220052, -0.99144486137381,   0.130526192220051, -0.923879532511287,  0.38268343236509,
        -0.793353340291235,  0.608761429008721, -0.608761429008721,  0.793353340291235, -0.38268343236509,   0.923879532511287, -0.130526192220052,  0.99144486137381,
         0.130526192220052,  0.99144486137381,   0.38268343236509,   0.923879532511287,  0.608761429008721,  0.793353340291235,  0.793353340291235,  0.608761429008721,
         0.923879532511287,  0.38268343236509,   0.99144486137381,   0.130526192220051,  0.99144486137381,  -0.130526192220051,  0.923879532511287, -0.38268343236509,
         0.793353340291235, -0.60876142900872,   0.608761429008721, -0.793353340291235,  0.38268343236509,  -0.923879532511287,  0.130526192220052, -0.99144486137381,
        -0.130526192220052, -0.99144486137381,  -0.38268343236509,  -0.923879532511287, -0.608761429008721, -0.793353340291235, -0.793353340291235, -0.608761429008721,
        -0.923879532511287, -0.38268343236509,  -0.99144486137381,  -0.130526192220052, -0.99144486137381,   0.130526192220051, -0.923879532511287,  0.38268343236509,
        -0.793353340291235,  0.608761429008721, -0.608761429008721,  0.793353340291235, -0.38268343236509,   0.923879532511287, -0.130526192220052,  0.99144486137381,
         0.130526192220052,  0.99144486137381,   0.38268343236509,   0.923879532511287,  0.608761429008721,  0.793353340291235,  0.793353340291235,  0.608761429008721,
         0.923879532511287,  0.38268343236509,   0.99144486137381,   0.130526192220051,  0.99144486137381,  -0.130526192220051,  0.923879532511287, -0.38268343236509,
         0.793353340291235, -0.60876142900872,   0.608761429008721, -0.793353340291235,  0.38268343236509,  -0.923879532511287,  0.130526192220052, -0.99144486137381,
        -0.130526192220052, -0.99144486137381,  -0.38268343236509,  -0.923879532511287, -0.608761429008721, -0.793353340291235, -0.793353340291235, -0.608761429008721,
        -0.923879532511287, -0.38268343236509,  -0.99144486137381,  -0.130526192220052, -0.99144486137381,   0.130526192220051, -0.923879532511287,  0.38268343236509,
        -0.793353340291235,  0.608761429008721, -0.608761429008721,  0.793353340291235, -0.38268343236509,   0.923879532511287, -0.130526192220052,  0.99144486137381,
         0.38268343236509,   0.923879532511287,  0.923879532511287,  0.38268343236509,   0.923879532511287, -0.38268343236509,   0.38268343236509,  -0.923879532511287,
        -0.38268343236509,  -0.923879532511287, -0.923879532511287, -0.38268343236509,  -0.923879532511287,  0.38268343236509,  -0.38268343236509,   0.923879532511287,
    };

    private final double[] GRAD3D = {0, 1, 1, 0,  0,-1, 1, 0,  0, 1,-1, 0,  0,-1,-1, 0,
        1, 0, 1, 0, -1, 0, 1, 0,  1, 0,-1, 0, -1, 0,-1, 0,
        1, 1, 0, 0, -1, 1, 0, 0,  1,-1, 0, 0, -1,-1, 0, 0,
        0, 1, 1, 0,  0,-1, 1, 0,  0, 1,-1, 0,  0,-1,-1, 0,
        1, 0, 1, 0, -1, 0, 1, 0,  1, 0,-1, 0, -1, 0,-1, 0,
        1, 1, 0, 0, -1, 1, 0, 0,  1,-1, 0, 0, -1,-1, 0, 0,
        0, 1, 1, 0,  0,-1, 1, 0,  0, 1,-1, 0,  0,-1,-1, 0,
        1, 0, 1, 0, -1, 0, 1, 0,  1, 0,-1, 0, -1, 0,-1, 0,
        1, 1, 0, 0, -1, 1, 0, 0,  1,-1, 0, 0, -1,-1, 0, 0,
        0, 1, 1, 0,  0,-1, 1, 0,  0, 1,-1, 0,  0,-1,-1, 0,
        1, 0, 1, 0, -1, 0, 1, 0,  1, 0,-1, 0, -1, 0,-1, 0,
        1, 1, 0, 0, -1, 1, 0, 0,  1,-1, 0, 0, -1,-1, 0, 0,
        0, 1, 1, 0,  0,-1, 1, 0,  0, 1,-1, 0,  0,-1,-1, 0,
        1, 0, 1, 0, -1, 0, 1, 0,  1, 0,-1, 0, -1, 0,-1, 0,
        1, 1, 0, 0, -1, 1, 0, 0,  1,-1, 0, 0, -1,-1, 0, 0,
        1, 1, 0, 0,  0,-1, 1, 0, -1, 1, 0, 0,  0,-1,-1, 0
    };

    public Noise(int seed) {
        this(seed, 0.01, null);
    }

    public Noise(int seed, double frequency) {
        this(seed, frequency, null);
    }

    public Noise(int seed, Fractal fractalBase) {
        this(seed, 0.01, fractalBase);
    }

    public Noise(int seed, double frequency, Fractal fractalBase) {
        this.seed = seed;
        this.frequency = frequency;
        this.fractalBase = fractalBase;
    }

    public double getNoise(double x, double y) {
        x *= frequency;
        y *= frequency;

        int originalSeed = seed;
        double sum = 0, amplitude;
        try {
            switch (fractalBase.fractalType()) {
                case FBM:
                    amplitude = fractalBase.fractalBounding();
                    for (int i = 0; i < fractalBase.octaves(); i++) {
                        double noise = get2DNoise(x, y);
                        sum += noise * amplitude;
                        amplitude *= linearInterpolation(1.0, ((noise + 1 < 2) ? noise + 1 : 2) * 0.5, fractalBase.weightedStrength());

                        x *= fractalBase.lacunarity();
                        y *= fractalBase.lacunarity();
                        amplitude *= fractalBase.persistence();
                        seed++;
                    }
                    seed = originalSeed;
                    return (Math.abs(sum) > 1) ? Math.signum(sum) : sum;
                case BILLOW:
                    amplitude = fractalBase.fractalBounding();
                    for (int i = 0; i < fractalBase.octaves(); i++) {
                        double noise = Math.abs(get2DNoise(x, y)) * 2 - 1;
                        sum += noise * amplitude;
                        amplitude *= linearInterpolation(1.0, ((noise + 1 < 2) ? noise + 1 : 2) * 0.5, fractalBase.weightedStrength());

                        x *= fractalBase.lacunarity();
                        y *= fractalBase.lacunarity();
                        amplitude *= fractalBase.persistence();
                        seed++;
                    }
                    seed = originalSeed;
                    return (Math.abs(sum) > 1) ? Math.signum(sum) : sum;
                case RIGID_MULTI:
                    amplitude = fractalBase.fractalBounding();
                    for (int i = 0; i < fractalBase.octaves(); i++) {
                        double noise = Math.abs(get2DNoise(x, y));
                        sum += (noise * -2 + 1) * amplitude;
                        amplitude *= linearInterpolation(1.0, 1 - noise, fractalBase.weightedStrength());

                        x *= fractalBase.lacunarity();
                        y *= fractalBase.lacunarity();
                        amplitude *= fractalBase.persistence();
                        seed++;
                    }
                    seed = originalSeed;
                    return (Math.abs(sum) > 1) ? Math.signum(sum) : sum;
                case PING_PONG:
                    amplitude = fractalBase.fractalBounding();
                    for (int i = 0; i < fractalBase.octaves(); i++) {
                        double noise = pingPong((get2DNoise(x, y) + 1) * ((PingPong) (fractalBase)).pingPongStrength());
                        sum += (noise - 0.5) * 2 * amplitude;
                        amplitude *= linearInterpolation(1.0, noise, fractalBase.weightedStrength());

                        x *= fractalBase.lacunarity();
                        y *= fractalBase.lacunarity();
                        amplitude *= fractalBase.persistence();
                        seed++;
                    }
                    seed = originalSeed;
                    return (Math.abs(sum) > 1) ? Math.signum(sum) : sum;
                default:
                    double result = get2DNoise(x, y);
                    return (Math.abs(result) > 1) ? Math.signum(result) : result;
            }
        } catch (NullPointerException ex) {
            double result = get2DNoise(x, y);
            return (Math.abs(result) > 1) ? Math.signum(result) : result;
        }
    }

    public double getNoise(double x, double y, double z) {
        x *= frequency;
        y *= frequency;
        z *= frequency;

        int originalSeed = seed;
        double sum = 0, amplitude;
        try {
            switch (fractalBase.fractalType()) {
                case FBM:
                    amplitude = fractalBase.fractalBounding();
                    for (int i = 0; i < fractalBase.octaves(); i++) {
                        double noise = get3DNoise(x, y, z);
                        sum += noise * amplitude;
                        amplitude *= linearInterpolation(1.0f, (noise + 1) * 0.5f, fractalBase.weightedStrength());

                        x *= fractalBase.lacunarity();
                        y *= fractalBase.lacunarity();
                        z *= fractalBase.lacunarity();
                        amplitude *= fractalBase.persistence();
                        seed++;
                    }
                    seed = originalSeed;
                    return (Math.abs(sum) > 1) ? Math.signum(sum) : sum;
                case BILLOW:
                    amplitude = fractalBase.fractalBounding();
                    for (int i = 0; i < fractalBase.octaves(); i++) {
                        double noise = Math.abs(get3DNoise(x, y, z)) * 2 - 1;
                        sum += noise * amplitude;
                        amplitude *= linearInterpolation(1.0f, (noise + 1) * 0.5f, fractalBase.weightedStrength());

                        x *= fractalBase.lacunarity();
                        y *= fractalBase.lacunarity();
                        z *= fractalBase.lacunarity();
                        amplitude *= fractalBase.persistence();
                        seed++;
                    }
                    seed = originalSeed;
                    return (Math.abs(sum) > 1) ? Math.signum(sum) : sum;
                case RIGID_MULTI:
                    amplitude = fractalBase.fractalBounding();
                    for (int i = 0; i < fractalBase.octaves(); i++) {
                        double noise = Math.abs(get3DNoise(x, y, z));
                        sum += (noise * -2 + 1) * amplitude;
                        amplitude *= linearInterpolation(1.0, 1 - noise, fractalBase.weightedStrength());

                        x *= fractalBase.lacunarity();
                        y *= fractalBase.lacunarity();
                        z *= fractalBase.lacunarity();
                        amplitude *= fractalBase.persistence();
                        seed++;
                    }
                    seed = originalSeed;
                    return (Math.abs(sum) > 1) ? Math.signum(sum) : sum;
                case PING_PONG:
                    amplitude = fractalBase.fractalBounding();
                    for (int i = 0; i < fractalBase.octaves(); i++) {
                        double noise = pingPong((get3DNoise(x, y, z) + 1) * ((PingPong) (fractalBase)).pingPongStrength());
                        sum += (noise - 0.5) * 2 * amplitude;
                        amplitude *= linearInterpolation(1.0, noise, fractalBase.weightedStrength());

                        x *= fractalBase.lacunarity();
                        y *= fractalBase.lacunarity();
                        z *= fractalBase.lacunarity();
                        amplitude *= fractalBase.persistence();
                        seed++;
                    }
                    seed = originalSeed;
                    return (Math.abs(sum) > 1) ? Math.signum(sum) : sum;
                default:
                    double result = get3DNoise(x, y, z);
                    return (Math.abs(result) > 1) ? Math.signum(result) : result;
                }
            } catch (NullPointerException ex) {
                double result = get3DNoise(x, y, z);
                return (Math.abs(result) > 1) ? Math.signum(result) : result;
            }
    }

    public abstract double get2DNoise(double x, double y);

    public abstract double get3DNoise(double x, double y, double z);

    public double[][] generateValues(int width, int height) {
        double[][] values = new double[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double value = this.getNoise(x, y);
                values[x][y] = value;
            }
        }
        return values;
    }

    public boolean[][] generateValues(double tolerance, boolean upperBound, int width, int height) {
        return (upperBound) ? generateValues(-1, tolerance, width, height) : generateValues(tolerance, 1, width, height);
    }

    public boolean[][] generateValues(double lowTolerance, double highTolerance, int width, int height) {
        boolean[][] values = new boolean[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double value = this.getNoise(x, y);
                values[x][y] = value <= highTolerance && value >= lowTolerance;
            }
        }
        return values;
    }

    public int[][] generateValues(int levels, int width, int height) {
        int[][] values = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double value = this.getNoise(x, y);
                int level = (int) (levels * (value + 1) / 2);
                if (level > levels - 1) {
                    level = levels - 1;
                }
                values[x][y] = level;
            }
        }
        return values;
    }

    protected int hash2D(int seed, int xPrimed, int yPrimed) {
        int hash = seed ^ xPrimed ^ yPrimed;
        hash *= 0x27d4eb2d;
        return hash;
    } 

    protected int hash3D(int seed, int xPrimed, int yPrimed, int zPrimed) {
        int hash = seed ^ xPrimed ^ yPrimed ^ zPrimed;
        hash *= 0x27d4eb2d;
        return hash;
    }

    protected double value2D(int seed, int xPrimed, int yPrimed) {
        int hash = hash2D(seed, xPrimed, yPrimed);

        hash *= hash;
        hash ^= hash << 19;
        return hash * (1 / 2147483648.0);
    }

    protected double value3D(int seed, int xPrimed, int yPrimed, int zPrimed) {
        int hash = hash3D(seed, xPrimed, yPrimed, zPrimed);

        hash *= hash;
        hash ^= hash << 19;
        return hash * (1 / 2147483648.0);
    }

    protected double gradient2D(int seed, int xPrimed, int yPrimed, double xd, double yd) {
        int hash = hash2D(seed, xPrimed, yPrimed);
        hash ^= hash >> 15;
        hash &= 127 << 1;

        double xg = GRAD2D[hash];
        double yg = GRAD2D[hash | 1];

        return xd * xg + yd * yg;
    }

    protected double gradient3D(int seed, int xPrimed, int yPrimed, int zPrimed, double xd, double yd, double zd) {
        int hash = hash3D(seed, xPrimed, yPrimed, zPrimed);
        hash ^= hash >> 15;
        hash &= 63 << 2;

        double xg = GRAD3D[hash];
        double yg = GRAD3D[hash | 1];
        double zg = GRAD3D[hash | 2];

        return xd * xg + yd * yg + zd * zg;
    }

    protected double linearInterpolation(double a, double b, double t) {
        return a + t * (b - a);
    }

    protected static double hermiteInterpolation(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    protected double trigonometricInterpolation(double t) {
        return (Math.sin(Math.PI * t - Math.PI / 2) + 1) / 2;
    }

    protected static double logisticInterpolation(double t) {
        return 1 / (1 + Math.exp(-10.5 * (t - 0.5)));
    }

    private double pingPong(double t) {
        t -= (int) (t * 0.5) * 2;
        return t < 1 ? t : 2 - t;
    }
}