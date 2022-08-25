
import javanoise.noise.*;
import javanoise.noise.fractal.*;
import javanoise.random.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        int seed = 1337;
        // double frequency = 0.01;
        // Fractal fractalBase = new FBM(5, 2.00, 0.5, 0.0f);
        // Noise noise = new Perlin(seed, frequency, InterpolationType.HERMITE, fractalBase);

        // NoiseMapGenerator.generateMap(noise, 256, 256, "Noisemap");

        RandomNumberGenerator rng = new LCG(2048);
        int[][] data = new int[20][1];
        for (int i = 0; i < 1000; i++) {
            int value = (int) Math.floor(rng.nextGaussian(0, 0.25) * 10.0);
            switch (value) {
                case -10:
                    data[0][0]++;
                    break;
                case -9:
                    data[1][0]++;
                    break;
                case -8:
                    data[2][0]++;
                    break;
                case -7:
                    data[3][0]++;
                    break;
                case -6:
                    data[4][0]++;
                    break;
                case -5:
                    data[5][0]++;
                    break;
                case -4:
                    data[6][0]++;
                    break;
                case -3:
                    data[7][0]++;
                    break;
                case -2:
                    data[8][0]++;
                    break;
                case -1:
                    data[9][0]++;
                    break;
                case 0:
                    data[10][0]++;
                    break;
                case 1:
                    data[11][0]++;
                    break;
                case 2:
                    data[12][0]++;
                    break;
                case 3: 
                    data[13][0]++;
                    break;
                case 4:
                    data[14][0]++;
                    break;
                case 5:
                    data[15][0]++;
                    break;
                case 6:
                    data[16][0]++;
                    break;
                case 7:
                    data[17][0]++;
                    break;
                case 8:
                    data[18][0]++;
                    break;
                case 9:
                    data[19][0]++;
                    break;
            }
        }

        for (int i = 0; i < 20; i++) {
            System.out.println(data[i][0]);
        }
    }
}
