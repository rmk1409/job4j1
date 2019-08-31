package ru.job4j.tracker;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class FindAllItem extends BaseAction {

    public FindAllItem(int key, String info) {
        super(key, info);
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
}
