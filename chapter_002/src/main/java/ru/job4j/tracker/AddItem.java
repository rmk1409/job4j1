package ru.job4j.tracker;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class AddItem implements UserAction {
    private int key;
    private String info;

    public AddItem(int key, String info) {
        this.key = key;
        this.info = info;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = input.ask("Введите имя заявки :");
        String desc = input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
        System.out.println("---------------------------------");
    }

    @Override
    public String info() {
        return this.info;
    }
}
