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
    private Scanner scanner;

    public InteractCalc() {
        this.scanner = new Scanner(System.in);
    }

    public InteractCalc(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Show all operations.
     */
    private void showMenu() {
        System.out.println("1. Multiply");
        System.out.println("2. Divide");
        System.out.println("3. Add");
        System.out.println("4. Subtract");
    }

    /**
     * Get required user input.
     *
     * @param msg to show
     * @return user input
     */
    private int getInput(String msg) {
        System.out.print(msg);
        return this.scanner.nextInt();
    }

    /**
     * Calculate basic math.
     */
    public void run() {
        this.showMenu();
        var operation = this.getInput("Choose operation: ");
        var first = this.getInput("Input 1st number: ");
        var second = this.getInput("Input 2nd number: ");
        switch (operation) {
            case 1:
                Calculator.multiply(first, second);
                break;
            case 2:
                Calculator.div(first, second);
                break;
            case 3:
                Calculator.add(first, second);
                break;
            case 4:
                Calculator.subtract(first, second);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        new InteractCalc().run();
    }
}
