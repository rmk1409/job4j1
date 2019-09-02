package ru.job4j.tracker;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class FindByNameItem extends BaseAction {

    public FindByNameItem(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Input input, Tracker tracker, Consumer<String> output) {
        output.accept("------------------- Search the request by name -----------------");
        List<Item> items = tracker.findByName(input.ask("Введите имя заявки :"));
        if (items.isEmpty()) {
            output.accept("Заявки не найдены");
        } else {
            items.forEach(item -> output.accept(String.format("%s %s %s.", item.getId(), item.getName(), item.getDescription())));
        }
        output.accept("---------------------------------");
    }
}