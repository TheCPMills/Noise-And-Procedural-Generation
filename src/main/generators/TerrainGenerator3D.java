package main.generators;

import main.noise.*;
// import main.util.*;
import java.io.*;

public class TerrainGenerator3D {
    public static void generate(Noise noise, double amplitude) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("assets/Terrain/3D/" + noise.getClass().getSimpleName() + "Noise3DTerrain.obj"));

            // TODO: Generate 3D Terrain

            writer.close();
        } catch (IOException ex) {
            System.out.println("Could not complete operation\n" + ex.getMessage());
        }
    }
}
