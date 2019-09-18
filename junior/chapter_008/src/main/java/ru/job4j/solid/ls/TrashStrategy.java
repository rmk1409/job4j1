package ru.job4j.solid.ls;

/**
 * TODO Description
 * Created by roman.pogorelov on 17.09.2019
 */
public class TrashStrategy extends AbstractStrategy {
    public TrashStrategy(ControlQuality control) {
        super(control);
    }

    @Override
    public void execute(Food food) {
        this.getControl()
                .getTrash()
                .getStorage()
                .add(food);
    }
}
