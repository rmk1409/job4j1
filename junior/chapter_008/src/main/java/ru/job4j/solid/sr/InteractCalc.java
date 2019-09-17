package ru.job4j.solid.sr;

import ru.job4j.calculator.Calculator;
import ru.job4j.solid.oc.EngineerCalc;

import java.util.*;

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
    protected List<String> menu = new ArrayList<>();
    protected Map<Integer, Operation> operations = new HashMap<>();

    {
        this.menu.add("1. Multiply");
        this.menu.add("2. Divide");
        this.menu.add("3. Add");
        this.menu.add("4. Subtract");
    }

    {
        this.operations.put(1, Calculator::multiply);
        this.operations.put(2, Calculator::div);
        this.operations.put(3, Calculator::add);
        this.operations.put(4, Calculator::subtract);
    }


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
        this.menu.forEach(System.out::println);
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
        var first = this.getInput("Input a number: ");
        var second = 0;
        if (operation > 0 && operation < 5) {
            second = this.getInput("Input the 2nd number: ");
        }
        this.operations.get(operation).calc(first, second);
    }

    public static void main(String[] args) {
        InteractCalc cal = new EngineerCalc();
        cal.run();
    }
}
