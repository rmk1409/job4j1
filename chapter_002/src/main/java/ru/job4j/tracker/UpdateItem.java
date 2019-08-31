package ru.job4j.tracker;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class UpdateItem implements UserAction {
    private int key;
    private String info;

    public UpdateItem(int key, String info) {
        this.key = key;
        this.info = info;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------------ Редактирование заявки -----------------");
        String id = input.ask("Введите id редактируемой заявки :");
        String name = input.ask("Введите имя заявки :");
        String desc = input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        System.out.println(tracker.replace(id, item) ? "Заявка успешно изменена." : "Заявка не изменена.");
        System.out.println("---------------------------------");
    }

    @Override
    public String info() {
        return this.info;
    }
}