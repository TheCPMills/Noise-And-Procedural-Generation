package main.generators;

import main.util.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class TerrainGenerator25D {

    public static void generate(String noiseType, double amplitude) {
        try {
            BufferedImage referenceMap = ImageIO.read(new File("assets/NoiseMaps/" + noiseType + "Noise.png"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("assets/Terrain/2.5D/" + noiseType + "25DTerrain.obj"));
            double[][] blackness = ImageUtils.getTransparency(referenceMap);
            int rows = blackness.length;
            int columns = blackness[0].length;
            int squares = (rows - 1) * (columns - 1);
            for (int y = 0; y < blackness.length; y++) {
                for (int x = 0; x < blackness[y].length; x++) {
                    writer.append("v " + x + " " + (amplitude * blackness[x][y]) + " " + y + "\n");
                }
            }
            int factor = 1;
            for (int i = 1; i < squares + 1; i++) {

                if (i % columns == 0) {
                    factor++;
                    i++;
                }
                writer.append("f " + i + " " + (i + 1) + " " + ((columns * factor) + (i % columns) + 1) + " "
                        + ((columns * factor) + (i % columns)) + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("Could not complete operation\n" + ex.getMessage());
        }
    }
}