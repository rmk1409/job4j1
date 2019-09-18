package ru.job4j.solid.ls;

/**
 * TODO Description
 * Created by roman.pogorelov on 17.09.2019
 */
public class Warehouse extends Storage {

    @Override
    public boolean accept(Food food) {
        return 0.25 > food.getSpoiled();
    }
}
