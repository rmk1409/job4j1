package ru.job4j.tracker;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class FindByNameItem implements UserAction {
    private int key;
    private String info;

    public FindByNameItem(int key, String info) {
        this.key = key;
        this.info = info;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------------- Поиск заявок по имени -----------------");
        Item[] items = tracker.findByName(input.ask("Введите имя заявки :"));
        if (items.length == 0) {
            System.out.println("Заявки не найдены");
        } else {
            for (Item item : items) {
                System.out.println(String.format("%s %s %s.", item.getId(), item.getName(), item.getDescription()));
            }
        }
        System.out.println("---------------------------------");
    }

    @Override
    public String info() {
        return this.info;
    }
}