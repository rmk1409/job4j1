package ru.job4j.pattern.singleton;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class InnerTracker {
    private InnerTracker() {
    }

    public static InnerTracker getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final InnerTracker INSTANCE = new InnerTracker();
    }
}
