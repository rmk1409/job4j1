package ru.job4j.solid.oc;

import ru.job4j.solid.sr.Act;
import ru.job4j.solid.sr.ConsoleInput;

/**
 * TODO Description
 * Created by roman.pogorelov on 25.09.2019
 */
public class TanAct implements Act {
    @Override
    public void act(ConsoleInput input) {
        var number = input.askNextInput("Input a number: ");
        System.out.format("Tan(%d) = %.2f%n", number, Math.tan(Math.toRadians(number)));
    }

    @Override
    public String info() {
        return "Tan operation";
    }
}
