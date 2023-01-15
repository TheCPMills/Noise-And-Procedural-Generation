package javanoise.noise;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class NoiseMapGenerator {
    public static void generateMap(Noise noise, int width, int height) {
        generateMap(noise, width, height, noise.getClass().getSimpleName() + "Noise");
    }

    public static void generateMap(Noise noise, double tolerance, boolean upperBound, int width, int height) {
        generateMap(noise, tolerance, upperBound, width, height);
    }

    public static void generateMap(Noise noise, double lowTolerance, double highTolerance, int width, int height) {
        generateMap(noise, lowTolerance, highTolerance, width, height, noise.getClass().getSimpleName() + "Noise");
    }

    public static void generateMap(Noise noise, int levels, int width, int height) {
        generateMap(noise, levels, width, height, noise.getClass().getSimpleName() + "Noise");
    }

    public static void generateColorfulMap(Noise noise, int width, int height) {
        generateColorfulMap(noise, width, height, "Colorful " + noise.getClass().getSimpleName() + "Noise");
    }

    public static void generateMap(Noise noise, int width, int height, String fileName) {
        try {
            ImageIO.write(generate(noise, width, height), "png", new File(fileName + ".png"));
        } catch (IOException ex) {
        }
    }

    public static void generateMap(Noise noise, double tolerance, boolean upperBound, int width, int height,
            String fileName) {
        try {
            ImageIO.write(generate(noise, tolerance, upperBound, width, height), "png", new File(fileName + ".png"));
        } catch (IOException ex) {
        }
    }

    public static void generateMap(Noise noise, double lowTolerance, double highTolerance, int width, int height,
            String fileName) {
        try {
            ImageIO.write(generate(noise, lowTolerance, highTolerance, width, height), "png",
                    new File(fileName + ".png"));
        } catch (IOException ex) {
        }
    }

    public static void generateMap(Noise noise, int levels, int width, int height, String fileName) {
        try {
            ImageIO.write(generate(noise, levels, width, height), "png", new File(fileName + ".png"));
        } catch (IOException ex) {
        }
    }

    public static void generateColorfulMap(Noise noise, int width, int height, String fileName) {
        try {
            ImageIO.write(generateColorful(noise, width, height), "png", new File(fileName + ".png"));
        } catch (IOException ex) {
        }
    }

    public static BufferedImage generate(Noise noise, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        double[][] values = noise.generateValues(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double value = values[x][y];
                int rgb = 0x010101 * (int) ((value + 1) * 127.5);
                image.setRGB(x, y, rgb);
            }
        }
        return image;
    }

    public static BufferedImage generate(Noise noise, double tolerance, boolean upperBound, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        boolean[][] values = noise.generateValues(tolerance, upperBound, width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = values[x][y] ? -1 : 0;
                image.setRGB(x, y, rgb);
            }
        }
        return image;
    }

    public static BufferedImage generate(Noise noise, double lowTolerance, double highTolerance, int width,
            int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        boolean[][] values = noise.generateValues(lowTolerance, highTolerance, width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = values[x][y] ? -1 : 0;
                image.setRGB(x, y, rgb);
            }
        }
        return image;
    }

    public static BufferedImage generate(Noise noise, int levels, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        int[][] values = noise.generateValues(levels, width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int level = 255 * values[x][y] / (levels - 1);
                int rgb = (level << 16) + (level << 8) + level;
                image.setRGB(x, y, rgb);
            }
        }
        return image;
    }

    public static BufferedImage generateColorful(Noise noise, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        double[][] values = noise.generateValues(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double value = values[x][y];
                int hue = (int) ((value + 1) * 180);
                image.setRGB(x, y, hsvToRGB(hue));
            }
        }
        return image;
    }

    private static int hsvToRGB(int hue) {
        int[] RGB = new int[3];
        float sRED, sGreen, sBlue;

        float s = 1.0f;
        float v = 1.0f;

        float c = s * v;
        float x = c * (1 - (float) Math.abs((hue / 60.0f) % 2 - 1));
        float m = v - c;

        if (hue > 0 && hue < 60) {
            sRED = c;
            sGreen = x;
            sBlue = 0;
        } else if (hue >= 60 && hue < 120) {
            sRED = x;
            sGreen = c;
            sBlue = 0;
        } else if (hue >= 120 && hue < 180) {
            sRED = 0;
            sGreen = c;
            sBlue = x;
        } else if (hue >= 180 && hue < 240) {
            sRED = 0;
            sGreen = x;
            sBlue = c;
        } else if (hue >= 240 && hue < 300) {
            sRED = x;
            sGreen = 0;
            sBlue = c;
        } else {
            sRED = c;
            sGreen = 0;
            sBlue = x;
        }

        RGB[0] = (int) Math.round((sRED + m) * 255);
        RGB[1] = (int) Math.round((sGreen + m) * 255);
        RGB[2] = (int) Math.round((sBlue + m) * 255);

        return (RGB[0] << 16) + (RGB[1] << 8) + RGB[2];
    }
}