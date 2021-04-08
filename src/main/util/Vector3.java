package main.util;

public class Vector3 {
    double x, y, z;

    public Vector3(double x, double y, double z) {
        setXYZ(x, y, z);
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

    public Vector3 multiply(Vector3 vec) {
        return new Vector3(x * vec.getX(), y * vec.getY(), z * vec.getZ());
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

    public Vector3 divide(double factor) {
        return new Vector3(x / factor, y / factor, z / factor);
    }

    public Vector3 mod(double divisor) {
        return new Vector3(x % divisor, y % divisor, z % divisor);
    }

    public Vector3 pow(double exponent) {
        return new Vector3(Math.pow(x, exponent), Math.pow(y, exponent), Math.pow(z, exponent));
    }

    public Vector3 min(Vector3 vec) {
        return new Vector3(Math.min(x, vec.getX()), Math.min(y, vec.getY()), Math.min(z, vec.getZ()));
    }

    public Vector3 max(Vector3 vec) {
        return new Vector3(Math.max(x, vec.getX()), Math.max(y, vec.getY()), Math.max(z, vec.getZ()));
    }

    public Vector3 floor() {
        return new Vector3(Math.floor(x), Math.floor(y), Math.floor(z));
    }

    public Vector3 ceil() {
        return new Vector3(Math.ceil(x), Math.ceil(y), Math.ceil(z));
    }

    public Vector3 trunc() {
        return new Vector3((int) x, (int) y, (int) z);
    }

    public Vector3 fract() {
        return new Vector3(x % 1, y % 1, z % 1);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vector2 getXY() {
        return new Vector2(x, y);
    }

    public Vector2 getXZ() {
        return new Vector2(x, z);
    }

    public Vector2 getYZ() {
        return new Vector2(y, z);
    }

    public double get(int i) {
        switch (i) {
        case 0:
            return getX();
        case 1:
            return getY();
        case 2:
            return getZ();
        default:
            return Double.NaN;
        }
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setXY(double x, double y) {
        setX(x);
        setY(y);
    }

    public void setXZ(double x, double z) {
        setX(x);
        setY(z);
    }

    public void setYZ(double y, double z) {
        setX(y);
        setY(z);
    }

    public void setXYZ(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    public String toString() {
        return "<" + x + ", " + y + ", " + z + ">";
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