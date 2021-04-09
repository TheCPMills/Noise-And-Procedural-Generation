package main.noise;

public class Value extends Noise {
    public Value(int seed) {
        super(seed);
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
}