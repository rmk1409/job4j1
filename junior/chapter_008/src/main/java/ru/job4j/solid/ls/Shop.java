package ru.job4j.solid.ls;

/**
 * TODO Description
 * Created by roman.pogorelov on 17.09.2019
 */
public class Shop extends Storage {

    @Override
    public boolean accept(Food food) {
        return 1 > food.getSpoiled();
    }

    @Override
    public void add(Food food) {
        if (0.75 < food.getSpoiled()) {
            food.setDiscount(50.0);
        }
        super.add(food);
    }
}
