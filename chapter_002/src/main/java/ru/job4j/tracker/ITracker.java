package ru.job4j.tracker;

import java.util.List;

/**
 * TODO Description
 * Created by roman.pogorelov on 12.09.2019
 */
public interface ITracker {
    Item add(Item item);
    boolean replace(String id, Item item);
    boolean delete(String id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(String id);
}
