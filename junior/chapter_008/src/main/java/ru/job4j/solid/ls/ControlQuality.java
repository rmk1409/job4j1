package ru.job4j.solid.ls;

import java.util.List;

/**
 * Создать класс обработчик перераспределения продуктов в место использования.
 * <p>
 * Часть 1.
 * 3.1. Если срок годности израсходован меньше чем на 25% направить в Warehouse.
 * 3.2 Если срок годности от 25% до 75% направить в Shop
 * 3.3. Если срок годности больше 75% то выставить скидку на продукт и отправить в Shop
 * 3.4. Если срок годности вышел. Отправить продукт в мусорку.
 * <p>
 * Часть 2.
 * От хозяина предприятие пришло новое условие требование. На складе Warehouse не хватает место для хранения и поэтому нужно добавить новый склад. Ваше решение?
 * Еще одно новое условие. Появились продукты, которые можно переработать после исхода срока годности. Нужно расширить программу. Что бы продукты в флагом canReproduct отправлялись на переработку.
 * Еще одно требование. Овощи пришедшие на обработку и попадающие на клад. Должны храниться в специальном складе с низкой температурой. Ваши решения.
 * В данном задание надо сделать расширение кода за счет использование шаблона проектирование - декоратор - https://www.tutorialspoint.com/design_pattern/decorator_pattern.htm
 * <p>
 * Помните. Вы не можете изменять код первого задания. Только расширять его.
 * <p>
 * Нельзя использовать instanceOf или if ("Shop".equals(storage.getName()))
 * <p>
 * Created by roman.pogorelov on 17.09.2019
 */
public class ControlQuality {
    private List<Storage> storages;

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
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
