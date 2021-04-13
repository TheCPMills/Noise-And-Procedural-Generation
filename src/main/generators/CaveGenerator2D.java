package main.generators;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class CaveGenerator2D {
    private static BufferedImage REFERENCE_MAP;
    private static int WIDTH;
    private static int HEIGHT;
    private static double THRESHOLD;

    public static void generate(String referenceMap, int width, int height, double threshold) {
        try {
            init(referenceMap, width, height, threshold);
            BufferedImage terrainGeneration = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {
                    boolean meetsThreshold = ((getPixelColor(i, j) / 256.0) >= THRESHOLD);
                    if (meetsThreshold) {
                        terrainGeneration.setRGB(i, j, 0xffffff);
                    } else {
                        terrainGeneration.setRGB(i, j, 0x000000);
                    }
                }
            }

            ImageIO.write(terrainGeneration, "png", new File(referenceMap + "2DCave.png"));
        } catch (IOException ex) {
            System.out.println("Could not complete operation\n" + ex.getMessage());
        }
    }

    private static int getPixelColor(int x, int y) throws IOException {
        int clr = REFERENCE_MAP.getRGB(x, y);
        int red = (clr & 0x00ff0000) >> 16;
        int green = (clr & 0x0000ff00) >> 8;
        int blue = clr & 0x000000ff;
        if (red != green || green != blue || blue != red) {
            System.out.println("Somehow, this color is not a shade of grey. But not to worry! It is all taken care of. Just thought you should know.");
        }
        return red;
    }

    private static void init(String referenceMap, int width, int height, double threshold) throws IOException {
        REFERENCE_MAP = ImageIO.read(new File("assets/NoiseMaps/" + referenceMap + "Noise.png"));
        WIDTH = width;
        HEIGHT = height;
        THRESHOLD = threshold;
    }
}