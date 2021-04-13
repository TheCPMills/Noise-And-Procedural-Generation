package main.noise;

public class Value extends Noise {
    
	public Value(int seed) {
        super(seed);
    }

	public Value(int seed, double frequency) {
        super(seed, frequency);
    }

	public Value(int seed, InterpolationType interpolation) {
        super(seed, interpolation);
    }

	public Value(int seed, double frequency, InterpolationType interpolation) {
        super(seed, frequency, interpolation);
    }

    public double getNoise(double x, double y) {
		x *= frequency;
        y *= frequency;
        
        int x0 = (x >= 0 ? (int) x : (int) x - 1);
		int y0 = (y >= 0 ? (int) y : (int) y - 1);
		int x1 = x0 + 1;
		int y1 = y0 + 1;

        double xs, ys;
		switch (interpolation) {
			default:
			case Linear:
				xs = x - x0;
				ys = y - y0;
				break;
			case Hermite:
				xs = hermiteInterpolation(x - x0);
				ys = hermiteInterpolation(y - y0);
				break;
			case Quintic:
				xs = quinticInterpolation(x - x0);
				ys = quinticInterpolation(y - y0);
				break;
		}

		double xf0 = linearInterpolation(value2D(seed, x0, y0), value2D(seed, x1, y0), xs);
		double xf1 = linearInterpolation(value2D(seed, x0, y1), value2D(seed, x1, y1), xs);

		return linearInterpolation(xf0, xf1, ys);
	}

	public double getNoise(double x, double y, double z) {
		x *= frequency;
		y *= frequency;
		z *= frequency;

		int x0 = (x >= 0 ? (int) x : (int) x - 1);
		int y0 = (y >= 0 ? (int) y : (int) y - 1);
		int z0 = (z >= 0 ? (int) z : (int) z - 1);
		int x1 = x0 + 1;
		int y1 = y0 + 1;
		int z1 = z0 + 1;

		double xs, ys, zs;
		switch (interpolation) {
			default:
			case Linear:
				xs = x - x0;
				ys = y - y0;
				zs = z - z0;
				break;
			case Hermite:
				xs = hermiteInterpolation(x - x0);
				ys = hermiteInterpolation(y - y0);
				zs = hermiteInterpolation(z - z0);
				break;
			case Quintic:
				xs = quinticInterpolation(x - x0);
				ys = quinticInterpolation(y - y0);
				zs = quinticInterpolation(z - z0);
				break;
		}

		double xf00 = linearInterpolation(value3D(seed, x0, y0, z0), value3D(seed, x1, y0, z0), xs);
		double xf10 = linearInterpolation(value3D(seed, x0, y1, z0), value3D(seed, x1, y1, z0), xs);
		double xf01 = linearInterpolation(value3D(seed, x0, y0, z1), value3D(seed, x1, y0, z1), xs);
		double xf11 = linearInterpolation(value3D(seed, x0, y1, z1), value3D(seed, x1, y1, z1), xs);

		double yf0 = linearInterpolation(xf00, xf10, ys);
		double yf1 = linearInterpolation(xf01, xf11, ys);

		return linearInterpolation(yf0, yf1, zs);
	}
}