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

        double xs = (x - x0) * (x - x0) * (3 - 2 * (x - x0));
        double ys = (y - y0) * (y - y0) * (3 - 2 * (y - y0));

		double xf0 = hashValues(x0, y0) + xs * (hashValues(x1, y0) - hashValues(x0, y0));
		double xf1 = hashValues(x0, y1) + xs * (hashValues(x1, y1) - hashValues(x0, y1));

		return xf0 + ys * (xf1 - xf0);
	}

    private double hashValues(int x, int y) {
		int n = seed;
		n ^= 1619 * x;
		n ^= 31337 * y;

		return (n * n * n * 60493) / 2147483648.0;
	}
}