package main.generators;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Generator {
    private static BufferedImage REFERENCE_TERRAIN;
    private static BufferedImage REFERENCE_CAVE;
    private static int WIDTH;
    private static int HEIGHT;

    public static void generate(String referenceTerrain, String referenceCave) {
        try {
            init(referenceTerrain, referenceCave);
            BufferedImage terrainGeneration = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {
                    boolean meetsThreshold = isBlack(i,j);
                    if (meetsThreshold) {
                        terrainGeneration.setRGB(i, j, REFERENCE_TERRAIN.getRGB(i, j));
                    } else {
                        terrainGeneration.setRGB(i, j, 0xa5a5a5);
                    }
                }
            }

            ImageIO.write(terrainGeneration, "png", new File(referenceTerrain + "2D.png"));
        } catch (IOException ex) {
            System.out.println("Could not complete operation\n" + ex.getMessage());
        }
    }

    private static boolean isBlack(int x, int y) {
        return (REFERENCE_CAVE.getRGB(x, y) == -16777216) || (REFERENCE_TERRAIN.getRGB(x, y) == -9456168);
    }

    private static void init(String referenceTerrain, String referenceCave) throws IOException {
        REFERENCE_TERRAIN = ImageIO.read(new File("assets/Terrain/2D/" + referenceTerrain + "2DTerrain.png"));
        REFERENCE_CAVE = ImageIO.read(new File(referenceCave + "2DCave.png"));
        WIDTH = REFERENCE_TERRAIN.getWidth();
        HEIGHT = REFERENCE_TERRAIN.getHeight();
    }
}
