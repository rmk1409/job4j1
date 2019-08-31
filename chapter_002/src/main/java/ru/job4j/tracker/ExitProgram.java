package ru.job4j.tracker;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class ExitProgram extends BaseAction {

    public ExitProgram(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
    }
}