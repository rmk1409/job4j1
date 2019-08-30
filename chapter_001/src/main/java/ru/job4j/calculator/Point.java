package ru.job4j.calculator;

public class Point {
    private int x;
    private int y;
    private int z;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Calculate the distance between two points
     *
     * @param another
     * @return distance
     */
    public double distance(Point another) {
        return Math.sqrt(Math.pow(another.x - this.x, 2) + Math.pow(another.y - this.y, 2));
    }

    /**
     * Calculate the distance between two 3D points
     *
     * @param another
     * @return distance
     */
    public double distance3d(Point another) {
        return Math.sqrt(Math.pow(another.x - this.x, 2) + Math.pow(another.y - this.y, 2) + Math.pow(another.z - this.z, 2));
    }

    public static void main(String[] args) {
        Point point = new Point(0, 0);
        Point another = new Point(0, 0);
        System.out.println("result (0, 0) to (2, 0) " + point.distance(another));
        System.out.println(String.format("result (%d, %d) to (%d, %d) %.2f", 0, 1, 2, 3, new Point(0, 1).distance(new Point(2, 3))));
        System.out.println(String.format("result (%d, %d) to (%d, %d) %.2f", 0, 10, 2, 3, new Point(0, 10).distance(new Point(2, 3))));
        System.out.println(String.format("result (%d, %d) to (%d, %d) %.2f", 0, 1, 20, 3, new Point(0, 1).distance(new Point(20, 3))));
        System.out.println(String.format("result (%d, %d) to (%d, %d) %.2f", 0, 10, 20, 3, new Point(0, 10).distance(new Point(20, 3))));
    }
}