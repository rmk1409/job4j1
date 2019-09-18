package ru.job4j.solid.ls;

/**
 * TODO Description
 * Created by roman.pogorelov on 17.09.2019
 */
public class ShopStrategy extends AbstractStrategy {
    public ShopStrategy(ControlQuality control) {
        super(control);
    }

    @Override
    public void execute(Food food) {
        this.getControl()
                .getShop()
                .getStorage()
                .add(food);
    }
}