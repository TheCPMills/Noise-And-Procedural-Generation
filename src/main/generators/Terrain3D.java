package main.generators;

import main.util.*;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Terrain3D {

    public static void writeToFile() throws IOException {
        BufferedImage noiseMap = ImageIO.read(new File("PerlinNoise.png"));
        double[][] blackness = ImageUtils.getTransparency(noiseMap);
        BufferedWriter writer = new BufferedWriter(new FileWriter("test3D.obj"));

        int rows = blackness.length;
        int columns = blackness[0].length;
        int squares = (rows - 1) * (columns - 1);

        for (int y = 0; y < blackness.length; y++) {
            for (int x = 0; x < blackness[y].length; x++) {
                writer.append("v " + x + " " + (100 * blackness[x][y]) + " " + y + "\n");
            }
        }

        int factor = 1;
        for (int i = 1; i < squares + 1; i++) {
            
            if (i % columns == 0) {
                factor++;
                i++;
            }
            writer.append("f " + i + " " + (i + 1) + " " + ((columns * factor) + (i % columns) + 1) + " " + ((columns * factor) + (i % columns)) + "\n");
        }

        writer.close();
    }

    public static void main(String[] args) {
        try {
            Terrain3D.writeToFile();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}