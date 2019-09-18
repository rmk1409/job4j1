package ru.job4j.solid.ls;

/**
 * TODO Description
 * Created by roman.pogorelov on 17.09.2019
 */
public class ShopDiscountStrategy extends AbstractStrategy {
    public ShopDiscountStrategy(ControlQuality control) {
        super(control);
    }

    @Override
    public void execute(Food food) {
        food.setDiscount(50.0);
        this.getControl()
                .getShop()
                .getStorage()
                .add(food);
    }
}
