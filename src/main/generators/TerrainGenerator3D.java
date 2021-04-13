package main.generators;

import main.noise.*;
import java.util.*;
import java.io.*;

public class TerrainGenerator3D {
    public static void generate(Noise noise, double threshold) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("assets/Terrain/3D/" + noise.getClass().getSimpleName() + "Noise3DTerrain.obj"));
            boolean[][] adjacency = new boolean[16][16];
            int rows = adjacency.length;
            int columns = adjacency[0].length;
            int squares = (rows - 1) * (columns - 1);
            for (double z = 1; z < 10; z += 1) {
                for (int y = 0; y < adjacency.length; y++) {
                    for (int x = 0; x < adjacency[y].length; x++) {
                        double value = (16777216 * noise.getNoise(x, y, z)) + 16777216;
                        if (value >= threshold) {
                            adjacency[x][y] = true;
                            writer.append("v " + x + " " + z + " " + y + "\n");
                        } else {
                            adjacency[x][y] = false;
                            writer.append("v -1 -1 -1 \n");
                        }
                    }
                }

                Boolean[] flatAdjacency = flatten(adjacency);
                int factor = 1;
                for (int i = 1; i < squares + 1; i++) {
                    if (i % columns == 0) {
                        factor++;
                        i++;
                    }

                    List<Integer> connections = new ArrayList<Integer>();

                    if (flatAdjacency[i - 1]) {
                        connections.add(i);
                    }
                    if (flatAdjacency[i]) {
                        connections.add(i + 1);
                    }
                    if (flatAdjacency[(columns * factor) + (i % columns)]) {
                        connections.add((columns * factor) + (i % columns) + 1);
                    }
                    if (flatAdjacency[(columns * factor) + (i % columns) - 1]) {
                        connections.add((columns * factor) + (i % columns));
                    }

                    if (connections.size() == 3) {
                        writer.append("f " + connections.get(0) + " " + connections.get(1) + " " + connections.get(2) + "\n");
                    } else if (connections.size() == 4) {
                        writer.append("f " + connections.get(0) + " " + connections.get(1) + " " + connections.get(2) + " " + connections.get(3) + "\n");
                    }
                    
                }
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("Could not complete operation\n" + ex.getMessage());
        }
    }

    private static Boolean[] flatten(boolean[][] arr) {
        List<Boolean> list = new ArrayList<Boolean>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                list.add(arr[i][j]);
            }
        }
        return list.toArray(new Boolean[arr.length * arr[0].length]);
    }
}
