package ru.job4j.tracker;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class DeleteItem extends BaseAction {

    public DeleteItem(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------------- Удаление заявки -----------------");
        String id = input.ask("Введите id редактируемой заявки :");
        System.out.println(tracker.delete(id) ? "Заявка успешно удалена." : "Заявка не удалена.");
        System.out.println("---------------------------------");
    }
}