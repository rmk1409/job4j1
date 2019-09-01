package ru.job4j.pattern.singleton;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class EagerInitTracker {
    private static EagerInitTracker instance = new EagerInitTracker();

    private EagerInitTracker() {
    }

    public static EagerInitTracker getInstance() {
        return EagerInitTracker.instance;
    }
}
