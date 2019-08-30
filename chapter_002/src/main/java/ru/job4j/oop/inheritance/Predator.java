package ru.job4j.oop.inheritance;

/**
 * Simple class
 * Created by roman.pogorelov on 30.08.2019
 */
public class Predator extends Animal {
    public Predator(String name) {
        super(name);
        System.out.println(Predator.class);
    }
}
