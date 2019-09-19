package ru.job4j.solid.sr;

import ru.job4j.calculator.Calculator;

/**
 * TODO Description
 * Created by roman.pogorelov on 19.09.2019
 */
public class DivAct implements Act {
    @Override
    public void act(int... args) {
        Calculator.div(args[0], args[1]);
    }
}
