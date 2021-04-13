package main.noise;

public class Simplex extends Noise {

    public Simplex(int seed) {
        super(seed);
    }

	public Simplex(int seed, double frequency) {
        super(seed, frequency);
    }

	public Simplex(int seed, InterpolationType interpolation) {
        super(seed, interpolation);
    }

	public Simplex(int seed, double frequency, InterpolationType interpolation) {
        super(seed, frequency, interpolation);
    }

    public double getNoise(double x, double y) {
        x *= frequency;
        y *= frequency;
        
        double G2 = (3.0 - 1.7320508075688772935274463415059) / 6.0;
        double t = (x + y) * (0.5 * (1.7320508075688772935274463415059 - 1.0));

        int i = ((x + t) >= 0 ? (int) (x + t) : (int) (x + t) - 1);
        int j = ((y + t) >= 0 ? (int) (y + t) : (int) (y + t) - 1);

        t = (i + j) * G2;
        double X0 = i - t;
        double Y0 = j - t;

        double x0 = x - X0;
        double y0 = y - Y0;

        int i1, j1;
        if (x0 > y0) {
            i1 = 1;
            j1 = 0;
        } else {
            i1 = 0;
            j1 = 1;
        }
        
        double x1 = x0 - i1 + G2;
        double y1 = y0 - j1 + G2;
        double x2 = x0 - 1 + 2 * G2;
        double y2 = y0 - 1 + 2 * G2;

        double n0, n1, n2;

        t = (double) 0.5 - x0 * x0 - y0 * y0;
        if (t < 0) {
            n0 = 0;
        } else {
            t *= t;
            n0 = t * t * gradient2D(seed, i, j, x0, y0);
        }

        t = (double) 0.5 - x1 * x1 - y1 * y1;
        if (t < 0) {
            n1 = 0;
        } else {
            t *= t;
            n1 = t * t * gradient2D(seed, i + i1, j + j1, x1, y1);
        }

        t = (double) 0.5 - x2 * x2 - y2 * y2;
        if (t < 0) {
            n2 = 0;
        } else {
            t *= t;
            n2 = t * t * gradient2D(seed, i + 1, j + 1, x2, y2);
        }

        return 50 * (n0 + n1 + n2);
    }

    public double getNoise(double x, double y, double z) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        
        double F3 = 1.0 / 3.0;
        double G3 = 1.0 / 6.0;
        double G33 = G3 * 3 - 1;

        double t = (x + y + z) * (1 / 3.0);
		int i = ((x + t) >= 0 ? (int) (x + t) : (int) (x + t) - 1);
		int j = ((y + t) >= 0 ? (int) (y + t) : (int) (y + t) - 1);
		int k = ((z + t) >= 0 ? (int) (z + t) : (int) (z + t) - 1);

		t = (i + j + k) * G3;
		double x0 = x - (i - t);
		double y0 = y - (j - t);
		double z0 = z - (k - t);

		int i1, j1, k1;
		int i2, j2, k2;

		if (x0 >= y0) {
			if (y0 >= z0) {
				i1 = 1;
				j1 = 0;
				k1 = 0;
				i2 = 1;
				j2 = 1;
				k2 = 0;
			} else if (x0 >= z0) {
				i1 = 1;
				j1 = 0;
				k1 = 0;
				i2 = 1;
				j2 = 0;
				k2 = 1;
			} else {
				i1 = 0;
				j1 = 0;
				k1 = 1;
				i2 = 1;
				j2 = 0;
				k2 = 1;
			}
		} else {
			if (y0 < z0) {
				i1 = 0;
				j1 = 0;
				k1 = 1;
				i2 = 0;
				j2 = 1;
				k2 = 1;
			} else if (x0 < z0) {
				i1 = 0;
				j1 = 1;
				k1 = 0;
				i2 = 0;
				j2 = 1;
				k2 = 1;
			} else {
				i1 = 0;
				j1 = 1;
				k1 = 0;
				i2 = 1;
				j2 = 1;
				k2 = 0;
			}
		}

		double x1 = x0 - i1 + G3;
		double y1 = y0 - j1 + G3;
		double z1 = z0 - k1 + G3;
		double x2 = x0 - i2 + F3;
		double y2 = y0 - j2 + F3;
		double z2 = z0 - k2 + F3;
		double x3 = x0 + G33;
		double y3 = y0 + G33;
		double z3 = z0 + G33;

		double n0, n1, n2, n3;

		t = 0.6 - x0 * x0 - y0 * y0 - z0 * z0;
		if (t < 0) {
            n0 = 0;
        } else {
			t *= t;
			n0 = t * t * gradient3D(seed, i, j, k, x0, y0, z0);
		}

		t = 0.6 - x1 * x1 - y1 * y1 - z1 * z1;
		if (t < 0) {
            n1 = 0;
        } else {
			t *= t;
			n1 = t * t * gradient3D(seed, i + i1, j + j1, k + k1, x1, y1, z1);
		}

		t = 0.6 - x2 * x2 - y2 * y2 - z2 * z2;
		if (t < 0) {
            n2 = 0;
        } else {
			t *= t;
			n2 = t * t * gradient3D(seed, i + i2, j + j2, k + k2, x2, y2, z2);
		}

		t = 0.6 - x3 * x3 - y3 * y3 - z3 * z3;
		if (t < 0) {
            n3 = 0;
        } else {
			t *= t;
			n3 = t * t * gradient3D(seed, i + 1, j + 1, k + 1, x3, y3, z3);
		}

		return 32 * (n0 + n1 + n2 + n3);
    }
}
