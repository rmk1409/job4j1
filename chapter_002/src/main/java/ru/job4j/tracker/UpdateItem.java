package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class UpdateItem extends BaseAction {

    public UpdateItem(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Input input, Tracker tracker, Consumer<String> output) {
        output.accept("------------------ Редактирование заявки -----------------");
        String id = input.ask("Введите id редактируемой заявки :");
        String name = input.ask("Введите имя заявки :");
        String desc = input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        output.accept(tracker.replace(id, item) ? "Заявка успешно изменена." : "Заявка не изменена.");
        output.accept("---------------------------------");
    }
}