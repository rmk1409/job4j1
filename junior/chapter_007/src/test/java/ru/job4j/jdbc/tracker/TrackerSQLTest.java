package ru.job4j.jdbc.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.sql.SQLException;
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
    private TrackerSQL trackerSQL;

    @Before
    public void init() throws SQLException {
        trackerSQL = new TrackerSQL(ConnectionRollback.create(ConnectionFactory.getConnection("app.properties")));
    }

    @After
    public void close() throws Exception {
        trackerSQL.close();
    }

    @Test
    public void checkConnection() {
        assertThat(this.trackerSQL.init(), is(true));
    }

    @Test
    public void add() {
        Item added = trackerSQL.add(new Item("added", "descAdded"));
        assertNotNull(added);
        Item byId = trackerSQL.findById(added.getId());
        assertThat(byId.getId(), is(added.getId()));
        assertThat(byId.getName(), is(added.getName()));
        assertThat(byId.getDescription(), is(added.getDescription()));
    }

    @Test
    public void replace() {
        Item previous = new Item("replace", "descReplace");
        trackerSQL.add(previous);
        Item next = new Item("newReplace", "newDescReplace");
        next.setId(previous.getId());
        trackerSQL.replace(previous.getId(), next);
        Item byId = trackerSQL.findById(previous.getId());
        assertThat(byId.getName(), is(next.getName()));
        assertThat(byId.getDescription(), is(next.getDescription()));
    }

    @Test
    public void delete() {
        Item item = trackerSQL.add(new Item("delete1", "descr1"));
        Item item2 = trackerSQL.add(new Item("delete2", "descr2"));
        Item item3 = trackerSQL.add(new Item("delete3", "descr3"));
        assertThat(true, is(trackerSQL.delete(item.getId())));
        List<Item> actual = Arrays.asList(item2, item3);
        assertThat(actual, is(trackerSQL.findAll()));
    }

    @Test
    public void findAll() {
        assertThat(trackerSQL.findAll().size(), is(0));
        Item item = trackerSQL.add(new Item("delete1", "descr1"));
        Item item2 = trackerSQL.add(new Item("delete2", "descr2"));
        Item item3 = trackerSQL.add(new Item("delete3", "descr3"));
        List<Item> actual = Arrays.asList(item, item2, item3);
        assertThat(actual, is(trackerSQL.findAll()));
    }

    @Test
    public void findByName() {
        Item item = trackerSQL.add(new Item("delete1", "descr1"));
        Item item2 = trackerSQL.add(new Item("delete1", "descr3"));
        Item item3 = trackerSQL.add(new Item("delete1", "descr5"));
        trackerSQL.add(new Item("delete2", "descr2"));
        trackerSQL.add(new Item("delete4", "descr4"));
        List<Item> actual = Arrays.asList(item, item2, item3);
        assertThat(actual, is(trackerSQL.findByName(item.getName())));
    }

    @Test
    public void findById() {
        Item item = trackerSQL.add(new Item("delete1", "descr1"));
        trackerSQL.add(new Item("delete2", "descr2"));
        trackerSQL.add(new Item("delete1", "descr3"));
        assertThat(item, is(trackerSQL.findById(item.getId())));
    }
}