package ru.job4j.oop.inheritance;

/**
 * Child
 * Created by roman.pogorelov on 30.08.2019
 */
public class Surgeon extends Doctor {
    private int operations;

    public int getOperations() {
        return operations;
    }

    public boolean operate(Client client) {
        return true;
    }
}
