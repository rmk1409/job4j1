package ru.job4j.solid.sr;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TODO Description
 * Created by roman.pogorelov on 17.09.2019
 */
public class InteractCalcTest {

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
    public void run() {
        Scanner scanner = new Scanner("0\n10\n5\n-1");
        InteractCalc calc = new InteractCalc(new ConsoleInput(scanner), List.of(new MultiplyAct()));
        calc.start();
        String expected = new StringJoiner(System.lineSeparator())
                .add("Choose: ")
                .add("-1. Exit.")
                .add("0. Multiply operation.")
                .add("Input the 1st number: Input the 2nd number: 10.0 * 5.0 = 50.0")
                .add("-----------------------------")
                .add("Choose: ")
                .add("-1. Exit.")
                .add("0. Multiply operation.")
                .add("-----------------------------")
                .add("")
                .toString();
        assertThat(this.out.toString(), is(expected));
    }
}