package ru.job4j.tracker;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class ExitProgram implements UserAction {
    private int key;
    private String info;

    public ExitProgram(int key, String info) {
        this.key = key;
        this.info = info;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public void execute(Input input, Tracker tracker) {

    }

    @Override
    public String info() {
        return this.info;
    }
}