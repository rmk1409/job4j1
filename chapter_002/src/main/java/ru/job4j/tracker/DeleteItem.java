package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class DeleteItem extends BaseAction {

    public DeleteItem(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Input input, ITracker tracker, Consumer<String> output) {
        output.accept("------------------- Удаление заявки -----------------");
        String id = input.ask("Введите id редактируемой заявки :");
        output.accept(tracker.delete(id) ? "Заявка успешно удалена." : "Заявка не удалена.");
        output.accept("---------------------------------");
    }
}