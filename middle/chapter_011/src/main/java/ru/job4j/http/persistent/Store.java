package ru.job4j.http.persistent;

import ru.job4j.http.model.User;

import java.util.List;

/**
 * Создать интерфейс Store c методами add, update, delete, findAll, findById
 * <p>
 * Created by roman.pogorelov on 30.09.2019
 */
public interface Store {
    void add(User user);
    void update(User user);
    void delete(long id);
    List<User> findAll();
    User findById(long id);
}
