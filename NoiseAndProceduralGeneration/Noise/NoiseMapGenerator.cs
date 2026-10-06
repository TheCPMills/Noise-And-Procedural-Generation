using System.Drawing;
using System.Drawing.Imaging;

namespace NoiseAndProceduralGeneration.Noise;

public static class NoiseMapGenerator
{
    private static readonly string PROJECT_ROOT = Path.Combine(Path.GetFullPath(Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "..\\..\\..\\")), "assets");

    public static void GenerateMap(Noise noise, int width, int height)
    {
        GenerateMap(noise, width, height, noise.GetType().Name + "Noise");
    }

    public static void GenerateMap(Noise noise, float tolerance, bool isUpperBound, int width, int height)
    {
        GenerateMap(noise, tolerance, isUpperBound, width, height, noise.GetType().Name + "Noise");
    }

    public static void GenerateMap(Noise noise, float lowTolerance, float highTolerance, int width, int height)
    {
        GenerateMap(noise, lowTolerance, highTolerance, width, height, noise.GetType().Name + "Noise");
    }

    public static void GenerateMap(Noise noise, int levels, int width, int height)
    {
        GenerateMap(noise, levels, width, height, noise.GetType().Name + "Noise");
    }

    public static void GenerateColorfulMap(Noise noise, int width, int height)
    {
        GenerateColorfulMap(noise, width, height, "Colorful " + noise.GetType().Name + "Noise");
    }

    public static void GenerateColorfulMap(Noise noise, int levels, int width, int height)
    {
        GenerateColorfulMap(noise, levels, width, height, "Colorful " + noise.GetType().Name + "Noise");
    }

    public static void GenerateMap(Noise noise, int width, int height, string fileName)
    {
        try
        {
            using var bmp = Generate(noise, width, height);
            bmp.Save(Path.Combine(PROJECT_ROOT, fileName + ".png"), ImageFormat.Png);
        }
        catch { }
    }

    public static void GenerateMap(Noise noise, float tolerance, bool isUpperBound, int width, int height, string fileName)
    {
        try
        {
            using var bmp = Generate(noise, tolerance, isUpperBound, width, height);
            bmp.Save(Path.Combine(PROJECT_ROOT, fileName + ".png"), ImageFormat.Png);
        }
        catch { }
    }

    public static void GenerateMap(Noise noise, float lowTolerance, float highTolerance, int width, int height, string fileName)
    {
        try
        {
            using var bmp = Generate(noise, lowTolerance, highTolerance, width, height);
            bmp.Save(Path.Combine(PROJECT_ROOT, fileName + ".png"), ImageFormat.Png);
        }
        catch { }
    }

    public static void GenerateMap(Noise noise, int levels, int width, int height, string fileName)
    {
        try
        {
            using var bmp = Generate(noise, levels, width, height);
            bmp.Save(Path.Combine(PROJECT_ROOT, fileName + ".png"), ImageFormat.Png);
        }
        catch { }
    }

    public static void GenerateColorfulMap(Noise noise, int width, int height, string fileName)
    {
        try
        {
            using var bmp = GenerateColorful(noise, width, height);
            bmp.Save(Path.Combine(PROJECT_ROOT, fileName + ".png"), ImageFormat.Png);
        }
        catch { }
    }

    public static void GenerateColorfulMap(Noise noise, int levels, int width, int height, string fileName)
    {
        try
        {
            using var bmp = GenerateColorful(noise, levels, width, height);
            bmp.Save(Path.Combine(PROJECT_ROOT, fileName + ".png"), ImageFormat.Png);
        }
        catch { }
    }

    public static Bitmap Generate(Noise noise, int width, int height)
    {
        var bmp = new Bitmap(width, height, PixelFormat.Format24bppRgb);
        var values = noise.GenerateValues(width, height);

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                float value = values[x, y];
                int gray = (int)((value + 1) * 127.5);
                gray = Math.Clamp(gray, 0, 255);
                bmp.SetPixel(x, y, Color.FromArgb(gray, gray, gray));
            }
        }
        return bmp;
    }

    public static Bitmap Generate(Noise noise, float tolerance, bool isUpperBound, int width, int height)
    {
        var bmp = new Bitmap(width, height, PixelFormat.Format24bppRgb);
        var values = noise.GenerateValues(tolerance, isUpperBound, width, height);

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                bmp.SetPixel(x, y, values[x, y] ? Color.White : Color.Black);
            }
        }
        return bmp;
    }

    public static Bitmap Generate(Noise noise, float lowTolerance, float highTolerance, int width, int height)
    {
        var bmp = new Bitmap(width, height, PixelFormat.Format24bppRgb);
        var values = noise.GenerateValues(lowTolerance, highTolerance, width, height);

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                bmp.SetPixel(x, y, values[x, y] ? Color.White : Color.Black);
            }
        }
        return bmp;
    }

    public static Bitmap Generate(Noise noise, int levels, int width, int height)
    {
        var bmp = new Bitmap(width, height, PixelFormat.Format24bppRgb);
        var values = noise.GenerateValues(levels, width, height);

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int level = (levels <= 1) ? 0 : (255 * values[x, y] / (levels - 1));
                bmp.SetPixel(x, y, Color.FromArgb(level, level, level));
            }
        }
        return bmp;
    }

    public static Bitmap GenerateColorful(Noise noise, int width, int height)
    {
        var bmp = new Bitmap(width, height, PixelFormat.Format24bppRgb);
        var values = noise.GenerateValues(width, height);

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                float value = values[x, y];
                int hue = (int)((value + 1) * 180);
                bmp.SetPixel(x, y, HSVtoRGB(hue));
            }
        }
        return bmp;
    }

    public static Bitmap GenerateColorful(Noise noise, int levels, int width, int height)
    {
        var bmp = new Bitmap(width, height, PixelFormat.Format24bppRgb);
        var values = noise.GenerateValues(levels, width, height);

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                float pct = values[x, y] / (float)levels;
                int hue = (int)(pct * 360);
                bmp.SetPixel(x, y, HSVtoRGB(hue));
            }
        }
        return bmp;
    }

    private static Color HSVtoRGB(int hue)
    {
        float s = 1f;
        float v = 1f;

        float c = s * v;
        float x = c * (1 - MathF.Abs((hue / 60f) % 2 - 1));
        float m = v - c;

        float r, g, b;

        if (hue < 60) { r = c; g = x; b = 0; }
        else if (hue < 120) { r = x; g = c; b = 0; }
        else if (hue < 180) { r = 0; g = c; b = x; }
        else if (hue < 240) { r = 0; g = x; b = c; }
        else if (hue < 300) { r = x; g = 0; b = c; }
        else { r = c; g = 0; b = x; }

        int R = (int)((r + m) * 255);
        int G = (int)((g + m) * 255);
        int B = (int)((b + m) * 255);

        return Color.FromArgb(R, G, B);
    }
}