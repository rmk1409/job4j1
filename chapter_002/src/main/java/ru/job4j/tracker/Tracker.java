package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Item storage
 * Created by roman.pogorelov on 30.08.2019
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
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
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    /**
     * Метод наход заявку по id и заменяет её item.
     *
     * @param id   для поиска заявки
     * @param item новая заявка
     * @return результат работы
     */
    public boolean replace(String id, Item item) {
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
    public boolean delete(String id) {
        boolean result = false;
        for (Item item : items) {
            if (id.equals(item.getId())) {
                result = items.remove(item);
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
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод возвращает массив с заявками, с именем key.
     *
     * @param key имя для поиска
     * @return массив заявок с этим key
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (key.equals(this.items.get(i).getName())) {
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
    public Item findById(String id) {
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
