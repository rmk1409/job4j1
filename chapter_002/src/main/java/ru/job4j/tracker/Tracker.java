package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Item storage
 * Created by roman.pogorelov on 30.08.2019
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
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
        for (int i = 0; i < this.position; i++) {
            if (id.equals(this.items[i].getId())) {
                this.items[i] = item;
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
        for (int i = 0; i < position; i++) {
            if (id.equals(this.items[i].getId())) {
                if (i + 1 != this.position) {
                    System.arraycopy(this.items, i + 1, this.items, i, this.position - 1 - i);
                }
                this.items[--position] = null;
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
    public Item[] findAll() {
        return Arrays.copyOf(this.items, this.position);
    }

    /**
     * Метод возвращает массив с заявками, с именем key.
     *
     * @param key имя для поиска
     * @return массив заявок с этим key
     */
    public Item[] findByName(String key) {
        int size = 0;
        for (int i = 0; i < this.position; i++) {
            if (key.equals(this.items[i].getName())) {
                size++;
            }
        }
        Item[] result = new Item[0];
        if (size > 0) {
            result = new Item[size];
            for (int i = 0, j = 0; i < position; i++) {
                if (key.equals(this.items[i].getName())) {
                    result[j] = this.items[i];
                    j++;
                }
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
        for (int i = 0; i < position; i++) {
            if (id.equals(this.items[i].getId())) {
                result = this.items[i];
                break;
            }
        }
        return result;
    }
}
