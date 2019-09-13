package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Item storage
 * Created by roman.pogorelov on 30.08.2019
 */
public class Tracker implements ITracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    @Override
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private Long generateId() {
        Random rm = new Random();
        return rm.nextLong() + System.currentTimeMillis();
    }

    /**
     * Метод наход заявку по id и заменяет её item.
     *
     * @param id   для поиска заявки
     * @param item новая заявка
     * @return результат работы
     */
    @Override
    public boolean replace(Long id, Item item) {
        boolean result = false;
        for (int i = 0; i < items.size(); i++) {
            if (id.equals(this.items.get(i).getId())) {
                this.items.set(i, item);
                item.setId(id);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод ищет заявку по id и удаляет её.
     *
     * @param id для поиска
     * @return удалось ли удалить заявку с указанным id
     */
    @Override
    public boolean delete(Long id) {
        boolean result = false;
        for (int i = 0; i < items.size(); i++) {
            if (id.equals(items.get(i).getId())) {
                items.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает массив действующих заявок.
     *
     * @return массив с заявками
     */
    @Override
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод возвращает массив с заявками, с именем name.
     *
     * @param name имя для поиска
     * @return массив заявок с этим name
     */
    @Override
    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (name.equals(this.items.get(i).getName())) {
                result.add(this.items.get(i));
            }
        }
        return result;
    }

    /**
     * Метод ищет заявку по id и возвращает её.
     *
     * @param id для поиска
     * @return заявку, если не найдено то null
     */
    @Override
    public Item findById(Long id) {
        Item result = null;
        for (int i = 0; i < this.items.size(); i++) {
            if (id.equals(this.items.get(i).getId())) {
                result = this.items.get(i);
                break;
            }
        }
        return result;
    }
}
