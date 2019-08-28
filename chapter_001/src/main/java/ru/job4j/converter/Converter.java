package ru.job4j.converter;

public class Converter {

    public static int rubleToEuro(int value) {
        return value / 70;
    }

    public static int rubleToDollar(int value) {
        return value / 60;
    }

    public static int euroToRuble(int value) {
        return value * 70;
    }

    public static int dollarToRuble(int value) {
        return value * 60;
    }

    public static void main(String[] args) {
        int in = 140;
        int expected = 2;
        int out = rubleToEuro(in);
        boolean passed = expected == out;
        System.out.println("140 rubles are 2. Test result : " + passed);

        System.out.println("---------------------------------");
        in = 180;
        expected = 3;
        out = rubleToDollar(in);
        passed = expected == out;
        System.out.println("180 rubles are 3. Test result : " + passed);

        System.out.println("---------------------------------");
        in = 10;
        expected = 700;
        out = euroToRuble(in);
        passed = expected == out;
        System.out.println("10 euros are 700. Test result : " + passed);

        System.out.println("---------------------------------");
        in = 10;
        expected = 600;
        out = dollarToRuble(in);
        passed = expected == out;
        System.out.println("10 dollars are 600. Test result : " + passed);
    }
}