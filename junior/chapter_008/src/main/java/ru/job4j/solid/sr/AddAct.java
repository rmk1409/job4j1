package ru.job4j.solid.sr;

import ru.job4j.calculator.Calculator;

/**
 * TODO Description
 * Created by roman.pogorelov on 19.09.2019
 */
public class AddAct implements Act {
    @Override
    public void act(ConsoleInput input) {
        int first = input.askNextInput("Input the 1st number: ");
        int second = input.askNextInput("Input the 2nd number: ");
        Calculator.add(first, second);
    }

    @Override
    public String info() {
        return "Add operation";
    }
}
