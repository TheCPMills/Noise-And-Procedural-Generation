package main.noise;

import main.util.*;

public class Cellular extends Noise {
    private double amplitude = 1;
    private double jitter = 1;
    private final double[] OFFSET_F = {-0.5, 0.5, 1.5};
    private final double K = 1.0 / 7.0;
    private final double Ko = 3.0 / 7.0;
    PermutationTable perm = new PermutationTable(1024, 255, seed);

    public Cellular(int seed) {
        super(seed);
    }

    public double getNoise(double x, double y) {
        x *= frequency;
        y *= frequency;

        int Pi0 = (int) (x + 1);
        int Pi1 = (int) (y + 1);

        double Pf0 = x % 1;
        double Pf1 = y % 1;

        Vector3 pX = new Vector3(perm.get(Pi0 - 1), perm.get(Pi0), perm.get(Pi0 + 1));

        double d0, d1, d2;
        double F0 = Double.POSITIVE_INFINITY;
        double F1 = Double.POSITIVE_INFINITY;
        double F2 = Double.POSITIVE_INFINITY;

        int px, py, pz;
        double oxx, oxy, oxz;
        double oyx, oyy, oyz;

        for(int i = 0; i < 3; i++) {
                px = perm.D2((int) pX.get(i), Pi1 - 1);
                py = perm.D2((int) pX.get(i), Pi1);
                pz = perm.D2((int) pX.get(i), Pi1 + 1);

                oxx = (px * K) % 1 - Ko;
                oxy = (py * K) % 1 - Ko;
                oxz = (pz * K) % 1 - Ko;

                oyx = ((int) (px * K)) % 7.0 * (K - Ko);
                oyy = ((int) (py * K)) % 7.0 * (K - Ko);
                oyz = ((int) (pz * K)) % 7.0 * (K - Ko);

                d0 = distance(Pf0, Pf1, OFFSET_F[i] + jitter * oxx, -0.5f + jitter * oyx);
                d1 = distance(Pf0, Pf1, OFFSET_F[i] + jitter * oxy, 0.5f + jitter * oyy);
                d2 = distance(Pf0, Pf1, OFFSET_F[i] + jitter * oxz, 1.5f + jitter * oyz);

                if (d0 < F0) {
                    F2 = F1; F1 = F0; F0 = d0;
                } else if (d0 < F1) {
                    F2 = F1; F1 = d0;
                } else if (d0 < F2) {
                    F2 = d0;
                }

                if (d1 < F0) {
                    F2 = F1; F1 = F0; F0 = d1;
                } else if (d1 < F1) {
                    F2 = F1; F1 = d1;
                } else if (d1 < F2) {
                    F2 = d1;
                }

                if (d2 < F0) {
                    F2 = F1; F1 = F0; F0 = d2;
                } else if (d2 < F1) {
                    F2 = F1; F1 = d2;
                } else if (d2 < F2) {
                    F2 = d2;
                }
            }

        return (F1 - F0) * amplitude;
    }

    private double distance(double p1x, double p1y, double p2x, double p2y) {
        return (p1x - p2x) * (p1x - p2x) + (p1y - p2y) * (p1y - p2y);
    }
}


class PermutationTable {

    private int size;
    private int seed;
    private int max;
    private int wrap;
    private int[] table;

    PermutationTable(int size, int max, int seed) {
        this.size = size;
        wrap = size - 1;
        this.max = Math.max(1, max);
        build(seed);
    }

    private void build(int seed) {
        if (this.seed == seed && table != null) {
            return;
        }

        this.seed = seed;
        table = new int[size];

        java.util.Random rnd = new java.util.Random(seed);

        for(int i = 0; i < size; i++) {
            table[i] = rnd.nextInt();
        }
    }

    public int get(int i) {
        return table[i];
    }

    public int D2(int i, int j) {
        return table[(j + table[i & wrap]) & wrap] & max;
    }

    public int D3(int i, int j, int k) {
        return table[(k + table[(j + table[i & wrap]) & wrap]) & wrap] & max;
    }
}
