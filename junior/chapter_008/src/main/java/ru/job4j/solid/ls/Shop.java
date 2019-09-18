package ru.job4j.solid.ls;

/**
 * TODO Description
 * Created by roman.pogorelov on 17.09.2019
 */
public class Shop extends Storage {

    @Override
    public boolean accept(Food food) {
        var result = false;
        double spoiled = food.getSpoiled();
        if (0.75 > spoiled) {
            this.getStorage().add(food);
            result = true;
        } else if (1 > spoiled) {
            food.setDiscount(50.0);
            this.getStorage().add(food);
            result = true;
        }
        return result;
    }
}
