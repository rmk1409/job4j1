package ru.job4j.synchronization;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TODO Description
 * Created by roman.pogorelov on 26.09.2019
 */
public class UserStorageTest {
    private UserStorage store;

    @Before
    public void init() {
        this.store = new UserStorage();
    }

    @Test
    public void add() {
        assertThat(this.store.getUsers().size(), is(0));
        this.store.add(new User(1, 10));
        assertThat(this.store.getUsers().size(), is(1));
        User user = new User(1, 20);
        this.store.add(user);
        assertThat(this.store.getUsers().size(), is(1));
    }

    @Test
    public void update() {
        this.store.add(new User(1, 10));
        assertThat(this.store.getUsers().size(), is(1));
        User user = new User(1, 20);
        this.store.update(user);
        assertThat(this.store.getUsers().size(), is(1));
    }

    @Test
    public void delete() {
        User user = new User(1, 10);
        this.store.add(user);
        assertThat(this.store.getUsers().size(), is(1));
        this.store.delete(user);
        assertThat(this.store.getUsers().size(), is(0));
    }

    @Test
    public void transfer() {
        this.store.add(new User(1, 100));
        this.store.add(new User(2, 200));
        this.store.transfer(1, 2, 50);
        assertThat(this.store.getUsers().get(1).getAmount(), is(50));
        assertThat(this.store.getUsers().get(2).getAmount(), is(250));
    }
}