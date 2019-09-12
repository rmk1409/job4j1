package ru.job4j.jdbc.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * TODO Description
 * Created by roman.pogorelov on 12.09.2019
 */
public class TrackerSQLTest {
    private TrackerSQL jdbc = new TrackerSQL();

    {
        this.jdbc.init();
    }

    @After
    public void clearAfter() {
        jdbc.clearDB();
    }

    @Before
    public void clear() {
        jdbc.clearDB();
    }

    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

    @Test
    public void add() {
        Item added = jdbc.add(new Item("added", "descAdded"));
        assertNotNull(added);
        Item byId = jdbc.findById(added.getId());
        assertThat(byId.getId(), is(added.getId()));
        assertThat(byId.getName(), is(added.getName()));
        assertThat(byId.getDescription(), is(added.getDescription()));
    }

    @Test
    public void replace() {
        Item previous = new Item("replace", "descReplace");
        jdbc.add(previous);
        Item next = new Item("newReplace", "newDescReplace");
        next.setId(previous.getId());
        jdbc.replace(previous.getId(), next);
        Item byId = jdbc.findById(previous.getId());
        assertThat(byId.getName(), is(next.getName()));
        assertThat(byId.getDescription(), is(next.getDescription()));
    }

    @Test
    public void delete() {
        Item item = jdbc.add(new Item("delete1", "descr1"));
        Item item2 = jdbc.add(new Item("delete2", "descr2"));
        Item item3 = jdbc.add(new Item("delete3", "descr3"));
        Item item4 = jdbc.add(new Item("delete4", "descr4"));
        Item item5 = jdbc.add(new Item("delete5", "descr5"));
        assertThat(true, is(jdbc.delete(item.getId())));
        List<Item> actual = Arrays.asList(item2, item3, item4, item5);
        assertThat(actual, is(jdbc.findAll()));
    }

    @Test
    public void findAll() {
        assertThat(jdbc.findAll().size(), is(0));
        Item item = jdbc.add(new Item("delete1", "descr1"));
        Item item2 = jdbc.add(new Item("delete2", "descr2"));
        Item item3 = jdbc.add(new Item("delete3", "descr3"));
        Item item4 = jdbc.add(new Item("delete4", "descr4"));
        Item item5 = jdbc.add(new Item("delete5", "descr5"));
        List<Item> actual = Arrays.asList(item, item2, item3, item4, item5);
        assertThat(actual, is(jdbc.findAll()));
    }

    @Test
    public void findByName() {
        Item item = jdbc.add(new Item("delete1", "descr1"));
        jdbc.add(new Item("delete2", "descr2"));
        Item item3 = jdbc.add(new Item("delete1", "descr3"));
        jdbc.add(new Item("delete4", "descr4"));
        Item item5 = jdbc.add(new Item("delete1", "descr5"));
        List<Item> actual = Arrays.asList(item, item3, item5);
        assertThat(actual, is(jdbc.findByName(item.getName())));
    }

    @Test
    public void findById() {
        Item item = jdbc.add(new Item("delete1", "descr1"));
        jdbc.add(new Item("delete2", "descr2"));
        jdbc.add(new Item("delete1", "descr3"));
        jdbc.add(new Item("delete4", "descr4"));
        jdbc.add(new Item("delete1", "descr5"));
        assertThat(item, is(jdbc.findById(item.getId())));
    }
}