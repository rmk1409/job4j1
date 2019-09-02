package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public interface UserAction {
    /**
     * Метод возвращает ключ опции.
     *
     * @return ключ
     */
    int key();

    /**
     * Основной метод.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     * @param output
     */
    void execute(Input input, Tracker tracker, Consumer<String> output);

    /**
     * Метод возвращает информацию о данном пункте меню.
     *
     * @return Строка меню
     */
    String info();
}
