package ru.job4j.http.logic;

import ru.job4j.http.model.User;
import ru.job4j.http.persistent.MemoryStore;
import ru.job4j.http.persistent.Store;

import java.util.List;

/**
 * Далее создайте класс ValidateService - это слой Logic. В нем нужно добавить методы
 * add, update, delete, findAll, findById
 * Каждый метод должен производить валидацию данных. Например, при обновлении полей нужно проверить. что такой пользователь существует.
 * <p>
 * Класс ValidateService сделать синглетоном. Использовать Eager initialization.
 * <p>
 * Created by roman.pogorelov on 30.09.2019
 */
public class ValidateService implements Store {
    private final static Store INSTANCE = new ValidateService();
    private final Store persistent = MemoryStore.getInstance();

    private ValidateService() {
    }

    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        this.persistent.add(user);
    }

    @Override
    public void update(User user) {
        if (this.persistent.findById(user.getId()) != null) {
            this.persistent.update(user);
        }
    }

    @Override
    public void delete(long id) {
        if (this.persistent.findById(id) != null) {
            this.persistent.delete(id);
        }
    }

    @Override
    public List<User> findAll() {
        return this.persistent.findAll();
    }

    @Override
    public User findById(long id) {
        return this.persistent.findById(id);
    }
}
