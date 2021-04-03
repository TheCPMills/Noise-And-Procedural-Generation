package main.generators;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class TerrainGenerator {
    private static BufferedImage REFERENCE_MAP;
    private static int WIDTH;
    private static int HEIGHT;
    private static double SEPARATION;
    private static int REFERENCE_ROW;

    public static void generate(String referenceMap, int width, int height, int separation, int referenceRow) {
        try {
            init(referenceMap, width, height, separation, referenceRow);
            BufferedImage terrainGeneration = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < WIDTH; i++) {
                int location = (HEIGHT / 2) + ((int) ((getPixelColor(i, REFERENCE_ROW) / SEPARATION) + 0.5)) - ((int) (128 / SEPARATION));
                for (int j = 0; j < HEIGHT; j++) {
                    if (j > location) {
                        terrainGeneration.setRGB(i, j, 0x299432);
                    } else if (j == location) {
                        terrainGeneration.setRGB(i, j, 0x60301a);
                    } else {
                        terrainGeneration.setRGB(i, j, 0x6fb5d8);
                    }
                }
            }
            for (int i = 0; i < WIDTH; i++) {
                terrainGeneration.setRGB(i, HEIGHT / 2, 0xffffff);
            }
            ImageIO.write(terrainGeneration, "png", new File(referenceMap.substring(0, referenceMap.indexOf(".png")) + "Terrain.png"));
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

    private static void init(String referenceMap, int width, int height, int separation, int referenceRow) throws IOException {
        REFERENCE_MAP = ImageIO.read(new File(referenceMap));
        WIDTH = width;
        HEIGHT = height;
        SEPARATION = separation;
        REFERENCE_ROW = referenceRow;
    }
}