package ru.job4j.calculator;

/**
 * Allows to do the basic arithmetic operation(+,/,*,-)
 * @author roman.pogorelov
 * @since 28.08.2019
 * @version 1
 */
public class Calculator {
    /**
     * Main method for testing add, div, multiply and subtract methods.
     * @param args
     */
    public static void main(String[] args) {
        add(1, 1);
        div(4, 2);
        multiply(2, 1);
        subtract(10, 5);
    }

    public static void add(double first, double second) {
        double result = first + second;
        System.out.println(first + " + " + second + " = " + result);
    }

    public static void div(double first, double second) {
        double result = first / second;
        System.out.println(first + " / " + second + " = " + result);
    }

    public static void multiply(double first, double second) {
        double result = first * second;
        System.out.println(first + " * " + second + " = " + result);
    }

    public static void subtract(double first, double second) {
        double result = first - second;
        System.out.println(first + " - " + second + " = " + result);
    }
}
