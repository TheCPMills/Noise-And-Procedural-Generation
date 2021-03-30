package main;

public class Vector3 {
    double x, y, z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double magnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public Vector3 add(Vector3 vec) {
        return new Vector3(x + vec.x, y + vec.y, z + vec.z);
    }

    public Vector3 add(double factor) {
        return new Vector3(x + factor, y + factor, z + factor);
    }

    public Vector3 subtract(Vector3 vec) {
        return new Vector3(x - vec.x, y - vec.y, z - vec.z);
    }

    public Vector3 subtract(double factor) {
        return new Vector3(x - factor, y - factor, z - factor);
    }

    public Vector3 multiply(double factor) {
        return new Vector3(x * factor, y * factor, z * factor);
    }

    public double dot(Vector3 vec) {
        return dot(this, vec);
    }

    public Vector3 cross(Vector3 vec) {
        return cross(this, vec);
    }

    public double angle(Vector3 vec) {
        return angle(this, vec);
    }

    public String toString() {
        return "<" + x + ", " + y + ", " + z + ">";
    }

    public Vector3 divide(double factor) {
        return new Vector3(x / factor, y / factor, z / factor);
    }

    public Vector3 mod(double divisor) {
        return new Vector3(x % divisor, y % divisor, z % divisor);
    }

    public static double dot(Vector3 vec1, Vector3 vec2) {
        return (vec1.x * vec2.x) + (vec1.y * vec2.y) + (vec1.z * vec2.z);
    }

    public static Vector3 cross(Vector3 vec1, Vector3 vec2) {
        double x1 = (vec1.y * vec2.z) - (vec1.z * vec2.y);
        double y1 = (vec1.z * vec2.x) - (vec1.x * vec2.z);
        double z1 = (vec1.x * vec2.y) - (vec1.y * vec2.x);
        return new Vector3(x1, y1, z1);
    }

    public static double angle(Vector3 vec1, Vector3 vec2) {
        return Math.acos(dot(vec1, vec2) / (vec1.magnitude() * vec2.magnitude()));
    }
}