package ru.job4j.tracker;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class FindItemById implements UserAction {
    private int key;
    private String info;

    public FindItemById(int key, String info) {
        this.key = key;
        this.info = info;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------------- Поиск заявки по id -----------------");
        Item item = tracker.findById(input.ask("Введите id заявки :"));
        String result = item == null ? "Заявка не найдена" : String.format("Заявка найдена - %s %s %s.", item.getId(), item.getName(), item.getDescription());
        System.out.println(result);
        System.out.println("---------------------------------");
    }

    @Override
    public String info() {
        return this.info;
    }
}
