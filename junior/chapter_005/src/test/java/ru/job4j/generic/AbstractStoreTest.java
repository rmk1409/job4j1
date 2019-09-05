package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO Description
 * Created by roman.pogorelov on 05.09.2019
 */
public class AbstractStoreTest {
    private AbstractStore<Role> store = new RoleStore(10);

    @Test
    public void add() {
        Role model = new Role("5");
        store.add(model);
        assertThat(model, is(store.findById("5")));
        assertNull(store.findById("7"));
    }

    @Test
    public void replace() {
        store.add(new Role("10"));
        Role model = new Role("7");
        store.replace("10", model);
        assertThat(model, is(store.findById("7")));
    }

    @Test
    public void delete() {
        store.add(new Role("10"));
        assertTrue(store.delete("10"));
        assertNull(store.findById("10"));
    }

    @Test
    public void findById() {
        assertNull(store.findById("15"));
        Role model = new Role("15");
        store.add(model);
        assertThat(model, is(store.findById("15")));
    }
}