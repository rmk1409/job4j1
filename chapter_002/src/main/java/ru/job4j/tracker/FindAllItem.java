package ru.job4j.tracker;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class FindAllItem implements UserAction {
    private int key;
    private String info;

    public FindAllItem(int key, String info) {
        this.key = key;
        this.info = info;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("--------------- Показ всех заявок ---------------");
        Item[] all = tracker.findAll();
        if (all.length == 0) {
            System.out.println("Заявок нет.");
        } else {
            for (Item item : all) {
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
