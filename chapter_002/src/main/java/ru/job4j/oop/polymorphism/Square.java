package ru.job4j.oop.polymorphism;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder builder = new StringBuilder();
        builder.append("**");
        builder.append("**");
        return builder.toString();
    }
}
