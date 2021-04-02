package main;

import java.awt.image.*;

public class ImageUtils {
    public static BufferedImage flipDiagonal(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(image.getHeight(), image.getWidth(), BufferedImage.TYPE_INT_RGB);
        int[][] pixels = getPixels(image);
        for (int x = 0; x < image.getHeight(); x++) {
            for (int y = 0; y < image.getWidth(); y++) {
                int color = pixels[y][x];
                newImage.setRGB(x, y, color);
            }
        }
        return newImage;
    }

    public static int[][] getPixels(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] pixelArray = new int[width][height];

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                pixelArray[x][y] = image.getRGB(x, y) % 256;

        return pixelArray;
    }
}
