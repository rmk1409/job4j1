package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 31.08.2019
 */
public class StartUITest {
    /**
     * Menu string
     */
    private String menu = new StringJoiner(
            System.lineSeparator())
            .add("0. Add new item")
            .add("1. Show all items")
            .add("2. Edit item")
            .add("3. Delete item")
            .add("4. Find item by Id")
            .add("5. Find items by name")
            .add("6. Exit Program")
            .toString();

    /**
     * Item storage
     */
    private Tracker tracker;

    /**
     * Вывод в консоль по дефолту
     */
    private final PrintStream stdout = System.out;
    /**
     * Буфер для результата.
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void beforeTest() {
        tracker = new Tracker();
        System.out.println("Starting test...");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void afterTest() {
        System.setOut(this.stdout);
        System.out.println("Ending test...");
    }

    @Test
    public void showAllEmpty() {
        StartUI ui = new StartUI(new StubInput(new String[]{"1", "y"}), tracker);
        ui.init();
        assertThat(this.out.toString(), is(
                new StringJoiner(
                        System.lineSeparator(), "",
                        System.lineSeparator())
                        .add(menu)
                        .add("--------------- Показ всех заявок ---------------")
                        .add("Заявок нет.")
                        .add("---------------------------------")
                        .toString()
                )
        );
    }

    @Test
    public void showAllNotEmpty() {
        Item first = new Item("alex", "Descr");
        this.tracker.add(first);
        Item second = new Item("alex", "Descr2");
        this.tracker.add(second);
        StartUI ui = new StartUI(new StubInput(new String[]{"1", "y"}), tracker);
        ui.init();
        assertThat(this.out.toString(), is(
                new StringJoiner(
                        System.lineSeparator(), "",
                        System.lineSeparator())
                        .add(menu)
                        .add("--------------- Показ всех заявок ---------------")
                        .add(String.format("%s %s %s.", first.getId(), first.getName(), first.getDescription()))
                        .add(String.format("%s %s %s.", second.getId(), second.getName(), second.getDescription()))
                        .add("---------------------------------")
                        .toString()
                )
        );
    }

    @Test
    public void findById() {
        Item first = new Item("alex", "Descr");
        this.tracker.add(first);
        StartUI ui = new StartUI(new StubInput(new String[]{"4", first.getId(), "y"}), tracker);
        ui.init();
        assertThat(this.out.toString(), is(
                new StringJoiner(
                        System.lineSeparator(), "",
                        System.lineSeparator())
                        .add(menu)
                        .add("------------------- Поиск заявки по id -----------------")
                        .add(String.format("Заявка найдена - %s %s %s.", first.getId(), first.getName(), first.getDescription()))
                        .add("---------------------------------")
                        .toString()
                )
        );
    }

    @Test
    public void findByName() {
        Item first = new Item("alex", "Descr");
        this.tracker.add(first);
        Item second = new Item("alex", "Descr2");
        this.tracker.add(second);
        Item third = new Item("max", "Descr3");
        this.tracker.add(third);
        StartUI ui = new StartUI(new StubInput(new String[]{"5", first.getName(), "y"}), tracker);
        ui.init();
        assertThat(this.out.toString(), is(
                new StringJoiner(
                        System.lineSeparator(), "",
                        System.lineSeparator())
                        .add(menu)
                        .add("------------------- Поиск заявок по имени -----------------")
                        .add(String.format("%s %s %s.", first.getId(), first.getName(), first.getDescription()))
                        .add(String.format("%s %s %s.", second.getId(), second.getName(), second.getDescription()))
                        .add("---------------------------------")
                        .toString()
                )
        );
    }

    @Test
    public void createItem() {
        StartUI ui = new StartUI(new StubInput(new String[]{"0", "alex", "decr1", "y"}), tracker);
        ui.init();
        Item item = this.tracker.findByName("alex")[0];
        assertThat("alex", is(item.getName()));
        assertThat("decr1", is(item.getDescription()));
    }

    @Test
    public void editItem() {
        Item previous = new Item("test1", "Descr");
        this.tracker.add(previous);
        StartUI ui = new StartUI(new StubInput(new String[]{"2", previous.getId(), "alex", "decr1", "y"}), this.tracker);
        ui.init();
        Item another = this.tracker.findByName("alex")[0];
        assertThat("alex", is(another.getName()));
        assertThat("decr1", is(another.getDescription()));
    }

    @Test
    public void deleteItem() {
        Item item = new Item("test1", "Descr");
        this.tracker.add(item);
        StartUI ui = new StartUI(new StubInput(new String[]{"3", item.getId(), "y"}), this.tracker);
        Item find = this.tracker.findById(item.getId());
        assertThat(item, is(find));
        ui.init();
        assertThat(null, is(this.tracker.findById(item.getId())));
    }
}