package ru.job4j.solid.isp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TODO Description
 * Created by roman.pogorelov on 18.09.2019
 */
public class RootTest {
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
        System.out.println("Starting test...");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void afterTest() {
        System.setOut(this.stdout);
        System.out.println("Ending test...");
    }

    @Test
    public void test() {
        Root task1 = new Root("Task 1.", 0);
        Root task11 = new Root("Task 1.1", 1);
        Root task111 = new Root("Task 1.1.1", 2);
        Root task112 = new Root("Task 1.1.2", 2);
        Root task12 = new Root("Task 1.2", 1);
        task1.add(task11);
        task1.add(task12);
        task11.add(task111);
        task11.add(task112);
        task1.execute();
        String expected = new StringJoiner(System.lineSeparator())
                .add("Task 1.")
                .add("\tTask 1.1")
                .add("\t\tTask 1.1.1")
                .add("\t\tTask 1.1.2")
                .add("\tTask 1.2")
                .add("")
                .toString();
        assertThat(this.out.toString(), is(expected));
    }
}