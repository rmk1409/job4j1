package ru.job4j.solid.ls;

/**
 * TODO Description
 * Created by roman.pogorelov on 17.09.2019
 */
public class Trash extends Storage {

    @Override
    public boolean accept(Food food) {
        return true;
    }
}
