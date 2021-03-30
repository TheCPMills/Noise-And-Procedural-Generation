package main;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class NoiseMapGenerator {

    public static void generate(Noise noise, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double value = noise.getNoise(x, y);
                int rgb = 0x010101 * (int) ((value + 1) * 127.5);
                image.setRGB(x, y, rgb);
            }
        }
        try {
            ImageIO.write(image, "png", new File(noise.getClass().getSimpleName() + "Noise.png"));
            GaussianFilter.blur("WhiteNoise.png", 1);
        } catch (IOException ex) {
        }
    }
}