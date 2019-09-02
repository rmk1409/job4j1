package ru.job4j.tracker;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class FindAllItem extends BaseAction {

    public FindAllItem(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Input input, Tracker tracker, Consumer<String> output) {
        output.accept("--------------- All requests ---------------");
        List<Item> all = tracker.findAll();
        if (all.isEmpty()) {
            output.accept("No any requests.");
        } else {
            all.forEach(item -> output.accept(String.format("%s %s %s.", item.getId(), item.getName(), item.getDescription())));
        }
        output.accept("---------------------------------");
    }
}
