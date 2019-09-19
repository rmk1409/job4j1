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
    protected ConsoleInput input;
    protected Act act;

    public InteractCalc(ConsoleInput input, Act act) {
        this.input = input;
        this.act = act;
    }

    public void start() {
        int first = input.askNextInput("Input a number: ");
        int second = input.askNextInput("Input the 2nd number: ");
        act.act(first, second);
    }

    public static void main(String[] args) {
        new InteractCalc(
                new ConsoleInput(),
                new AddAct()
        ).start();
    }
}
