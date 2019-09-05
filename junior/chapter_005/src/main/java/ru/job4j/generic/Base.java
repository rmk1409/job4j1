package ru.job4j.generic;

/**
 * TODO Description
 * Created by roman.pogorelov on 05.09.2019
 */
public abstract class Base {
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}