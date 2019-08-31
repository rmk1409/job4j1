package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class StartUITest {

    @Test
    public void createItem() {
        Tracker tracker = new Tracker();
        StartUI ui = new StartUI(new StubInput(new String[]{"0", "alex", "decr1", "6"}), tracker);
        ui.init();
        Item item = tracker.findByName("alex")[0];
        assertThat("alex", is(item.getName()));
        assertThat("decr1", is(item.getDescription()));
    }

    @Test
    public void editItem() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "Descr");
        tracker.add(previous);
        StartUI ui = new StartUI(new StubInput(new String[]{"2", previous.getId(), "alex", "decr1", "6"}), tracker);
        ui.init();
        Item another = tracker.findByName("alex")[0];
        assertThat("alex", is(another.getName()));
        assertThat("decr1", is(another.getDescription()));
    }

    @Test
    public void deleteItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "Descr");
        tracker.add(item);
        StartUI ui = new StartUI(new StubInput(new String[]{"3", item.getId(), "6"}), tracker);
        Item find = tracker.findById(item.getId());
        assertThat(item, is(find));
        ui.init();
        assertThat(null, is(tracker.findById(item.getId())));
    }
}