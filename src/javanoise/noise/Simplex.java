package javanoise.noise;

import javanoise.noise.fractal.*;

public class Simplex extends Noise {
    public Simplex(int seed) {
        super(seed);
    }

    public Simplex(int seed, double frequency) {
        super(seed, frequency);
    }

    public Simplex(int seed, Fractal fractalBase) {
        super(seed, fractalBase);
    }

    public Simplex(int seed, double frequency, Fractal fractalBase) {
        super(seed, frequency, fractalBase);
    }

    public double get2DNoise(double x, double y) {
        final double F2 = 0.5f * (Math.sqrt(3.0) - 1);
        double t = (x + y) * F2;
        x += t;
        y += t;
        
        double G2 = (3.0 - 1.7320508075688772935274463415059) / 6.0;

        int i = (x >= 0) ? (int) x : (int) x - 1;
        int j = (y >= 0) ? (int) y : (int) y - 1;
        double xi = x - i;
        double yi = y - j;

        i *= PrimeX;
        j *= PrimeY;
        int i1 = i + PrimeX;
        int j1 = j + PrimeY;

        t = (xi + yi) * G2;
        double x0 = xi - t;
        double y0 = yi - t;

        double a0 = (2.0 / 3.0) - x0 * x0 - y0 * y0;
        double value = (a0 * a0) * (a0 * a0) * gradient2D(seed, i, j, x0, y0);

        double a1 = (double) (2 * (1 - 2 * G2) * (1 / G2 - 2)) * t + ((double) (-2 * (1 - 2 * G2) * (1 - 2 * G2)) + a0);
        double x1 = x0 - (double) (1 - 2 * G2);
        double y1 = y0 - (double) (1 - 2 * G2);
        value += (a1 * a1) * (a1 * a1) * gradient2D(seed, i1, j1, x1, y1);

        double xmyi = xi - yi;
        if (t > G2) {
            if (xi + xmyi > 1) {
                double x2 = x0 + (double) (3 * G2 - 2);
                double y2 = y0 + (double) (3 * G2 - 1);
                double a2 = (2.0f / 3.0f) - x2 * x2 - y2 * y2;
                if (a2 > 0) {
                    value += (a2 * a2) * (a2 * a2) * gradient2D(seed, i + (PrimeX << 1), j + PrimeY, x2, y2);
                }
            } else {
                double x2 = x0 + (double) G2;
                double y2 = y0 + (double) (G2 - 1);
                double a2 = (2.0f / 3.0f) - x2 * x2 - y2 * y2;
                if (a2 > 0) {
                    value += (a2 * a2) * (a2 * a2) * gradient2D(seed, i, j + PrimeY, x2, y2);
                }
            }

            if (yi - xmyi > 1) {
                double x3 = x0 + (double) (3 * G2 - 1);
                double y3 = y0 + (double) (3 * G2 - 2);
                double a3 = (2.0f / 3.0f) - x3 * x3 - y3 * y3;
                if (a3 > 0) {
                    value += (a3 * a3) * (a3 * a3) * gradient2D(seed, i + PrimeX, j + (PrimeY << 1), x3, y3);
                }
            } else {
                double x3 = x0 + (double) (G2 - 1);
                double y3 = y0 + (double) G2;
                double a3 = (2.0f / 3.0f) - x3 * x3 - y3 * y3;
                if (a3 > 0) {
                    value += (a3 * a3) * (a3 * a3) * gradient2D(seed, i + PrimeX, j, x3, y3);
                }
            }
        } else {
            if (xi + xmyi < 0) {
                double x2 = x0 + (double) (1 - G2);
                double y2 = y0 - (double) G2;
                double a2 = (2.0f / 3.0f) - x2 * x2 - y2 * y2;
                if (a2 > 0) {
                    value += (a2 * a2) * (a2 * a2) * gradient2D(seed, i - PrimeX, j, x2, y2);
                }
            } else {
                double x2 = x0 + (double) (G2 - 1);
                double y2 = y0 + (double) G2;
                double a2 = (2.0f / 3.0f) - x2 * x2 - y2 * y2;
                if (a2 > 0) {
                    value += (a2 * a2) * (a2 * a2) * gradient2D(seed, i + PrimeX, j, x2, y2);
                }
            }

            if (yi < xmyi) {
                double x2 = x0 - (double) G2;
                double y2 = y0 - (double) (G2 - 1);
                double a2 = (2.0f / 3.0f) - x2 * x2 - y2 * y2;
                if (a2 > 0) {
                    value += (a2 * a2) * (a2 * a2) * gradient2D(seed, i, j - PrimeY, x2, y2);
                }
            } else {
                double x2 = x0 + (double) G2;
                double y2 = y0 + (double) (G2 - 1);
                double a2 = (2.0f / 3.0f) - x2 * x2 - y2 * y2;
                if (a2 > 0) {
                    value += (a2 * a2) * (a2 * a2) * gradient2D(seed, i, j + PrimeY, x2, y2);
                }
            }
        }

        return value * 18.24196194486065;
    }

    public double get3DNoise(double x, double y, double z) {
        int i = (x >= 0) ? (int) x : (int) x - 1;
        int j = (y >= 0) ? (int) y : (int) y - 1;
        int k = (z >= 0) ? (int) z : (int) z - 1;
        
        double xi = x - i;
        double yi = y - j;
        double zi = z - k;

        i *= PrimeX;
        j *= PrimeY;
        k *= PrimeZ;
        int seed2 = seed + 1293373;

        int xNMask = (int) (-0.5f - xi);
        int yNMask = (int) (-0.5f - yi);
        int zNMask = (int) (-0.5f - zi);

        double x0 = xi + xNMask;
        double y0 = yi + yNMask;
        double z0 = zi + zNMask;
        double a0 = 0.75f - x0 * x0 - y0 * y0 - z0 * z0;
        double value = (a0 * a0) * (a0 * a0) * gradient3D(seed, i + (xNMask & PrimeX), j + (yNMask & PrimeY), k + (zNMask & PrimeZ), x0, y0, z0);

        double x1 = xi - 0.5f;
        double y1 = yi - 0.5f;
        double z1 = zi - 0.5f;
        double a1 = 0.75f - x1 * x1 - y1 * y1 - z1 * z1;
        value += (a1 * a1) * (a1 * a1) * gradient3D(seed2, i + PrimeX, j + PrimeY, k + PrimeZ, x1, y1, z1);

        double xAFlipMask0 = ((xNMask | 1) << 1) * x1;
        double yAFlipMask0 = ((yNMask | 1) << 1) * y1;
        double zAFlipMask0 = ((zNMask | 1) << 1) * z1;
        double xAFlipMask1 = (-2 - (xNMask << 2)) * x1 - 1.0f;
        double yAFlipMask1 = (-2 - (yNMask << 2)) * y1 - 1.0f;
        double zAFlipMask1 = (-2 - (zNMask << 2)) * z1 - 1.0f;

        boolean skip5 = false;
        double a2 = xAFlipMask0 + a0;
        if (a2 > 0) {
            double x2 = x0 - (xNMask | 1);
            double y2 = y0;
            double z2 = z0;
            value += (a2 * a2) * (a2 * a2) * gradient3D(seed, i + (~xNMask & PrimeX), j + (yNMask & PrimeY), k + (zNMask & PrimeZ), x2, y2, z2);
        } else {
            double a3 = yAFlipMask0 + zAFlipMask0 + a0;
            if (a3 > 0) {
                double x3 = x0;
                double y3 = y0 - (yNMask | 1);
                double z3 = z0 - (zNMask | 1);
                value += (a3 * a3) * (a3 * a3) * gradient3D(seed, i + (xNMask & PrimeX), j + (~yNMask & PrimeY), k + (~zNMask & PrimeZ), x3, y3, z3);
            }

            double a4 = xAFlipMask1 + a1;
            if (a4 > 0) {
                double x4 = (xNMask | 1) + x1;
                double y4 = y1;
                double z4 = z1;
                value += (a4 * a4) * (a4 * a4) * gradient3D(seed2, i + (xNMask & (PrimeX * 2)), j + PrimeY, k + PrimeZ, x4, y4, z4);
                skip5 = true;
            }
        }

        boolean skip9 = false;
        double a6 = yAFlipMask0 + a0;
        if (a6 > 0) {
            double x6 = x0;
            double y6 = y0 - (yNMask | 1);
            double z6 = z0;
            value += (a6 * a6) * (a6 * a6) * gradient3D(seed, i + (xNMask & PrimeX), j + (~yNMask & PrimeY), k + (zNMask & PrimeZ), x6, y6, z6);
        } else {
            double a7 = xAFlipMask0 + zAFlipMask0 + a0;
            if (a7 > 0) {
                double x7 = x0 - (xNMask | 1);
                double y7 = y0;
                double z7 = z0 - (zNMask | 1);
                value += (a7 * a7) * (a7 * a7) * gradient3D(seed, i + (~xNMask & PrimeX), j + (yNMask & PrimeY), k + (~zNMask & PrimeZ), x7, y7, z7);
            }

            double a8 = yAFlipMask1 + a1;
            if (a8 > 0) {
                double x8 = x1;
                double y8 = (yNMask | 1) + y1;
                double z8 = z1;
                value += (a8 * a8) * (a8 * a8) * gradient3D(seed2, i + PrimeX, j + (yNMask & (PrimeY << 1)), k + PrimeZ, x8, y8, z8);
                skip9 = true;
            }
        }

        boolean skipD = false;
        double aA = zAFlipMask0 + a0;
        if (aA > 0) {
            double xA = x0;
            double yA = y0;
            double zA = z0 - (zNMask | 1);
            value += (aA * aA) * (aA * aA) * gradient3D(seed, i + (xNMask & PrimeX), j + (yNMask & PrimeY), k + (~zNMask & PrimeZ), xA, yA, zA);
        } else {
            double aB = xAFlipMask0 + yAFlipMask0 + a0;
            if (aB > 0) {
                double xB = x0 - (xNMask | 1);
                double yB = y0 - (yNMask | 1);
                double zB = z0;
                value += (aB * aB) * (aB * aB) * gradient3D(seed, i + (~xNMask & PrimeX), j + (~yNMask & PrimeY), k + (zNMask & PrimeZ), xB, yB, zB);
            }

            double aC = zAFlipMask1 + a1;
            if (aC > 0) {
                double xC = x1;
                double yC = y1;
                double zC = (zNMask | 1) + z1;
                value += (aC * aC) * (aC * aC) * gradient3D(seed2, i + PrimeX, j + PrimeY, k + (zNMask & (PrimeZ << 1)), xC, yC, zC);
                skipD = true;
            }
        }

        if (!skip5) {
            double a5 = yAFlipMask1 + zAFlipMask1 + a1;
            if (a5 > 0) {
                double x5 = x1;
                double y5 = (yNMask | 1) + y1;
                double z5 = (zNMask | 1) + z1;
                value += (a5 * a5) * (a5 * a5) * gradient3D(seed2, i + PrimeX, j + (yNMask & (PrimeY << 1)), k + (zNMask & (PrimeZ << 1)), x5, y5, z5);
            }
        }

        if (!skip9) {
            double a9 = xAFlipMask1 + zAFlipMask1 + a1;
            if (a9 > 0) {
                double x9 = (xNMask | 1) + x1;
                double y9 = y1;
                double z9 = (zNMask | 1) + z1;
                value += (a9 * a9) * (a9 * a9) * gradient3D(seed2, i + (xNMask & (PrimeX * 2)), j + PrimeY, k + (zNMask & (PrimeZ << 1)), x9, y9, z9);
            }
        }

        if (!skipD) {
            double aD = xAFlipMask1 + yAFlipMask1 + a1;
            if (aD > 0) {
                double xD = (xNMask | 1) + x1;
                double yD = (yNMask | 1) + y1;
                double zD = z1;
                value += (aD * aD) * (aD * aD) * gradient3D(seed2, i + (xNMask & (PrimeX << 1)), j + (yNMask & (PrimeY << 1)), k + PrimeZ, xD, yD, zD);
            }
        }

        return value * 9.046026385208288f;
    }
}
