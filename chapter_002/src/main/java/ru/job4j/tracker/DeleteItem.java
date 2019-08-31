package ru.job4j.tracker;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class DeleteItem implements UserAction {
    private int key;
    private String info;

    public DeleteItem(int key, String info) {
        this.key = key;
        this.info = info;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------------- Удаление заявки -----------------");
        String id = input.ask("Введите id редактируемой заявки :");
        System.out.println(tracker.delete(id) ? "Заявка успешно удалена." : "Заявка не удалена.");
        System.out.println("---------------------------------");
    }

    @Override
    public String info() {
        return this.info;
    }
}