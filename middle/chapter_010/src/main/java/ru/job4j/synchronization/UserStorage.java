package ru.job4j.synchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 2. Класс хранилища пользователей UserStorage[#182028]
 * <p>
 * 1. Создать класс - структуру данных для хранение пользователей UserStorage.
 * 2. В заголовке класса обозначить аннотацию @ThreadSafe из библиотеки
 * <dependency>
 * <groupId>net.jcip</groupId>
 * <artifactId>jcip-annotations</artifactId>
 * <version>1.0</version>
 * </dependency>
 * <p>
 * 3. Хранилище должно иметь методы boolean add (User user), boolean update(User user), boolean delete(User user).
 * 4. И особый метод transfer(int fromId, int toId, int amount);
 * 5. Структура данных должна быть потокобезопасная;
 * 6. В структуре User Должны быть поля int id, int amount.
 * amount - это сумма денег на счете пользователя.
 * <p>
 * Пример. использования.
 * UserStore store = new UserStore();
 * <p>
 * store.add(new User(1, 100));
 * store.add(new User(2, 200));
 * <p>
 * store.transfer(1, 2, 50);
 * <p>
 * Created by roman.pogorelov on 26.09.2019
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    /**
     * Adds the input user to the storage.
     *
     * @param user is used to add
     * @return whether it is successful or not
     */
    public synchronized boolean add(User user) {
        return null != this.users.put(user.getId(), user);
    }

    /**
     * Updates the input user in the storage.
     *
     * @param user new data, is used to update
     * @return whether it is successful or not
     */
    public synchronized boolean update(User user) {
        boolean result = false;
        User found = this.users.get(user.getId());
        if (null != found) {
            this.users.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    /**
     * Deletes the input user from the storage
     *
     * @param user is used to delete
     * @return whether it is successful or not
     */
    public synchronized boolean delete(User user) {
        return null != this.users.remove(user.getId());
    }

    /**
     * Transfers the amount between the users.
     *
     * @param fromId is used to remove the amount
     * @param toId   is used to plus the amount
     * @param amount quantity
     */
    public synchronized void transfer(int fromId, int toId, int amount) {
        User from = this.users.get(fromId);
        User to = this.users.get(toId);
        if (from.getAmount() >= amount) {
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
        }
    }

    public synchronized Map<Integer, User> getUsers() {
        return users;
    }
}

class User {
    private final int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}