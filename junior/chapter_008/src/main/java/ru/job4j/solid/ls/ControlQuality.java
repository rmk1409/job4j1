package ru.job4j.solid.ls;

/**
 * Создать класс обработчик перераспределения продуктов в место использования.
 * <p>
 * 3.1. Если срок годности израсходован меньше чем на 25% направить в Warehouse.
 * 3.2 Если срок годности от 25% до 75% направить в Shop
 * 3.3. Если срок годности больше 75% то выставить скидку на продукт и отправить в Shop
 * 3.4. Если срок годности вышел. Отправить продукт в мусорку.
 * <p>
 * Created by roman.pogorelov on 17.09.2019
 */
public class ControlQuality {
    private Storage first;

    public ControlQuality(Storage first) {
        this.first = first;
    }

    public ControlQuality() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        warehouse.setNext(shop);
        shop.setNext(new Trash());
        this.first = warehouse;
    }

    public void sendProduct(Food food) {
        Storage current = this.first;
        while (true) {
            if (current.accept(food)) {
                current.add(food);
                break;
            } else {
                current = current.getNext();
            }
        }
    }

    public Storage getFirst() {
        return first;
    }
}
