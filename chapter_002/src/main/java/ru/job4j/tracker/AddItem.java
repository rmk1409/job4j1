package ru.job4j.tracker;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class AddItem extends BaseAction {

    public AddItem(int key, String info) {
        super(key, info);
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
}
