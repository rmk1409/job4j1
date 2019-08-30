package ru.job4j.oop;

/**
 * Simple class
 * Created by roman.pogorelov on 30.08.2019
 */
public class Battery {
    private int load;

    public void exchange(Battery another) {
        another.load += this.load;
        this.load = 0;
    }
}
