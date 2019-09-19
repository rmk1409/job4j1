package ru.job4j.solid.sr;

import ru.job4j.calculator.Calculator;

import java.util.Scanner;

/**
 * Используя класс Calculator.
 * 1. Сделать класс InteractCalc.
 * 2. В классе должен быть пользовательский ввод.
 * 3. Повторный выбор операции и переиспользование предыдущего вычисления.
 * 4. Проект должен следовать SRP.
 * <p>
 * Created by roman.pogorelov on 17.09.2019
 */
public class InteractCalc {
    protected Scanner scanner;
    protected String operation;

    public InteractCalc(String operation, Scanner scanner) {
        this.operation = operation;
        this.scanner = scanner;
    }

    public InteractCalc(String operation) {
        this.operation = operation;
        this.scanner = new Scanner(System.in);
    }

    public void calc() {
        System.out.print("Input a number: ");
        var first = this.scanner.nextInt();
        System.out.print("Input the 2nd number: ");
        var second = this.scanner.nextInt();
        switch (this.operation) {
            case "*":
                Calculator.multiply(first, second);
                break;
            case "/":
                Calculator.div(first, second);
                break;
            case "+":
                Calculator.add(first, second);
                break;
            case "-":
                Calculator.subtract(first, second);
                break;
            default:
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        InteractCalc cal = new InteractCalc("+");
        cal.calc();
    }
}
