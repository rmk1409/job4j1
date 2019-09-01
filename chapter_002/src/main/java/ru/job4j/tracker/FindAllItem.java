package ru.job4j.tracker;

import java.util.List;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class FindAllItem extends BaseAction {

    public FindAllItem(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("--------------- All requests ---------------");
        List<Item> all = tracker.findAll();
        if (all.isEmpty()) {
            System.out.println("No any requests.");
        } else {
            all.forEach(item -> System.out.println(String.format("%s %s %s.", item.getId(), item.getName(), item.getDescription())));
        }
        System.out.println("---------------------------------");
    }
}
