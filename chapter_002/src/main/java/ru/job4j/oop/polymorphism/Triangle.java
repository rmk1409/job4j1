package ru.job4j.oop.polymorphism;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class Triangle implements Shape {
    @Override
    public String draw() {
        StringBuilder builder = new StringBuilder();
        builder.append("  *").append(System.lineSeparator());
        builder.append(" ***").append(System.lineSeparator());
        builder.append("*****");
        return builder.toString();
    }
}
