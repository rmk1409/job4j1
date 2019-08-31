package ru.job4j.pattern.singleton;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class LazyInitTracker {
    private static LazyInitTracker instance;

    private LazyInitTracker() {
    }

    public static LazyInitTracker getInstance() {
        if (LazyInitTracker.instance == null) {
            LazyInitTracker.instance = new LazyInitTracker();
        }
        return LazyInitTracker.instance;
    }
}
