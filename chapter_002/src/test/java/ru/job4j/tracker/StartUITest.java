package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.Consumer;

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

    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

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
        StartUI ui = new StartUI(new StubInput(Arrays.asList("1", "y")), this.tracker, this.output);
        ui.init();
        assertThat(this.out.toString(), is(
                new StringJoiner(
                        System.lineSeparator(), "",
                        System.lineSeparator())
                        .add(menu)
                        .add("--------------- All requests ---------------")
                        .add("No any requests.")
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
        StartUI ui = new StartUI(new StubInput(Arrays.asList("1", "y")), this.tracker, this.output);
        ui.init();
        assertThat(this.out.toString(), is(
                new StringJoiner(
                        System.lineSeparator(), "",
                        System.lineSeparator())
                        .add(menu)
                        .add("--------------- All requests ---------------")
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
        StartUI ui = new StartUI(new StubInput(Arrays.asList("4", first.getId(), "y")), this.tracker, this.output);
        ui.init();
        assertThat(this.out.toString(), is(
                new StringJoiner(
                        System.lineSeparator(), "",
                        System.lineSeparator())
                        .add(menu)
                        .add("------------------- Search request by id -----------------")
                        .add(String.format("Request is found - %s %s %s.", first.getId(), first.getName(), first.getDescription()))
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
        StartUI ui = new StartUI(new StubInput(Arrays.asList("5", first.getName(), "y")), this.tracker, this.output);
        ui.init();
        assertThat(this.out.toString(), is(
                new StringJoiner(
                        System.lineSeparator(), "",
                        System.lineSeparator())
                        .add(menu)
                        .add("------------------- Search the request by name -----------------")
                        .add(String.format("%s %s %s.", first.getId(), first.getName(), first.getDescription()))
                        .add(String.format("%s %s %s.", second.getId(), second.getName(), second.getDescription()))
                        .add("---------------------------------")
                        .toString()
                )
        );
    }

    @Test
    public void createItem() {
        StartUI ui = new StartUI(new StubInput(Arrays.asList("0", "alex", "decr1", "y")), this.tracker, this.output);
        ui.init();
        Item item = this.tracker.findByName("alex").get(0);
        assertThat("alex", is(item.getName()));
        assertThat("decr1", is(item.getDescription()));
    }

    @Test
    public void editItem() {
        Item previous = new Item("test1", "Descr");
        this.tracker.add(previous);
        StartUI ui = new StartUI(new StubInput(Arrays.asList("2", previous.getId(), "alex", "decr1", "y")), this.tracker, this.output);
        ui.init();
        Item another = this.tracker.findByName("alex").get(0);
        assertThat("alex", is(another.getName()));
        assertThat("decr1", is(another.getDescription()));
    }

    @Test
    public void deleteItem() {
        Item item = new Item("test1", "Descr");
        this.tracker.add(item);
        StartUI ui = new StartUI(new StubInput(Arrays.asList("3", item.getId(), "y")), this.tracker, this.output);
        Item find = this.tracker.findById(item.getId());
        assertThat(item, is(find));
        ui.init();
        assertThat(null, is(this.tracker.findById(item.getId())));
    }
}