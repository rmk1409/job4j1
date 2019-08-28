package ru.job4j.calculator;

public class Point {
    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static void main(String[] args) {
        double result = distance(0, 0, 2, 0);
        System.out.println("result (0, 0) to (2, 0) " + result);

        System.out.println(String.format("result (%d, %d) to (%d, %d) %.2f", 0, 1, 2, 3, distance(0, 1, 2, 3)));
        System.out.println(String.format("result (%d, %d) to (%d, %d) %.2f", 0, 10, 2, 3, distance(0, 10, 2, 3)));
        System.out.println(String.format("result (%d, %d) to (%d, %d) %.2f", 0, 1, 20, 3, distance(0, 1, 20, 3)));
        System.out.println(String.format("result (%d, %d) to (%d, %d) %.2f", 0, 10, 20, 3, distance(0, 10, 20, 3)));
    }
}