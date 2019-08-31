package ru.job4j.tracker;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class ExitProgram extends BaseAction {
    private StartUI startUI;

    public ExitProgram(int key, String info, StartUI startUI) {
        super(key, info);
        this.startUI = startUI;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        this.startUI.setExit(true);
    }
}