package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by roman.pogorelov on 30.08.2019
 */
public class TrackerTest {

    @Test
    public void add() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void replace() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1");
        tracker.add(previous);
        Item next = new Item("test2");
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void deleteFirst() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        Item item4 = new Item("test4");
        Item item5 = new Item("test5");
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        assertThat(true, is(tracker.delete(item.getId())));
        Item[] actual = {item2, item3, item4, item5};
        assertThat(actual, is(tracker.findAll()));
    }

    @Test
    public void deleteMiddle() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        Item item4 = new Item("test4");
        Item item5 = new Item("test5");
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        assertThat(true, is(tracker.delete(item3.getId())));
        Item[] actual = {item, item2, item4, item5};
        assertThat(actual, is(tracker.findAll()));
    }

    @Test
    public void deleteLast() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        Item item4 = new Item("test4");
        Item item5 = new Item("test5");
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        assertThat(true, is(tracker.delete(item5.getId())));
        Item[] actual = {item, item2, item3, item4};
        assertThat(actual, is(tracker.findAll()));
    }

    @Test
    public void deleteFalse() {
        assertThat(false, is(new Tracker().delete("")));
    }

    @Test
    public void findAll() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        Item item4 = new Item("test4");
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        Item[] actual = {item, item2, item3, item4};
        assertThat(actual, is(tracker.findAll()));
    }

    @Test
    public void findByName() {
        Tracker tracker = new Tracker();
        Item item = new Item("test3");
        Item item2 = new Item("test3");
        tracker.add(new Item("test1"));
        tracker.add(new Item("test2"));
        tracker.add(item);
        tracker.add(item2);
        tracker.add(new Item("test4"));
        Item[] actual = {item, item2};
        assertThat(actual, is(tracker.findByName(item.getName())));
    }

    @Test
    public void findById() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test1"));
        tracker.add(new Item("test2"));
        tracker.add(new Item("test3"));
        tracker.add(new Item("test4"));
        tracker.add(new Item("test5"));
        tracker.add(new Item("test1"));
        Item item = new Item("test1");
        tracker.add(item);
        assertThat(item, is(tracker.findById(item.getId())));
    }

    @Test
    public void findByIdNull() {
        assertThat(null, is(new Tracker().findById("")));
    }
}