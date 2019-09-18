package ru.job4j.solid.ls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private List<Storage> storages;

    public ControlQuality() {
        this.storages = new ArrayList<>(Arrays.asList(new Warehouse(), new Shop(), new Trash()));
    }

    public void sendProduct(Food food) {
        for (Storage cur : this.storages) {
            if (cur.accept(food)) {
                cur.add(food);
                break;
            }
        }
    }

    public List<Storage> getStorages() {
        return storages;
    }

    public void setStorages(List<Storage> storages) {
        this.storages = storages;
    }
}
