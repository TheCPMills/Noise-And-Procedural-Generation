package main.noise;

public class Simplex extends Noise {

    public Simplex(int seed) {
        super(seed);
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
        if (t < 0)
            n0 = 0;
        else {
            t *= t;
            n0 = t * t * GradCoord2D(seed, i, j, x0, y0);
        }

        t = (double) 0.5 - x1 * x1 - y1 * y1;
        if (t < 0)
            n1 = 0;
        else {
            t *= t;
            n1 = t * t * GradCoord2D(seed, i + i1, j + j1, x1, y1);
        }

        t = (double) 0.5 - x2 * x2 - y2 * y2;
        if (t < 0)
            n2 = 0;
        else {
            t *= t;
            n2 = t * t * GradCoord2D(seed, i + 1, j + 1, x2, y2);
        }

        return 50 * (n0 + n1 + n2);
    }
    
}
