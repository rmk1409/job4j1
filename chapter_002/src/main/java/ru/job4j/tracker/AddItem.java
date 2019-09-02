package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class AddItem extends BaseAction {

    public AddItem(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Input input, Tracker tracker, Consumer<String> output) {
        output.accept("------------ Добавление новой заявки --------------");
        String name = input.ask("Введите имя заявки :");
        String desc = input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        tracker.add(item);
        output.accept("------------ Новая заявка с getId : " + item.getId() + "-----------");
        output.accept("---------------------------------");
    }
}
