package ru.job4j.http.servlet;

import ru.job4j.http.model.User;
import ru.job4j.http.persistent.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateServiceStub implements Store {
    private final Map<Long, User> innerStore = new HashMap<>();

    @Override
    public void add(User user) {
        this.innerStore.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        this.innerStore.put(user.getId(), user);
    }

    @Override
    public void delete(long id) {
        this.innerStore.remove(id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.innerStore.values());
    }

    @Override
    public User findById(long id) {
        throw new UnsupportedOperationException();
    }
}
