package ru.job4j.solid.ls;

/**
 * TODO Description
 * Created by roman.pogorelov on 17.09.2019
 */
public abstract class AbstractStrategy implements Strategy {
    private ControlQuality control;

    public AbstractStrategy(ControlQuality control) {
        this.control = control;
    }

    public ControlQuality getControl() {
        return control;
    }

    public void setControl(ControlQuality control) {
        this.control = control;
    }
}
