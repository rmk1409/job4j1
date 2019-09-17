package ru.job4j.solid.sr;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
        Scanner scanner = new Scanner("1\n10\n5\n");
        InteractCalc calc = new InteractCalc(scanner);
        calc.run();
        String expected = new StringJoiner(System.lineSeparator())
                .add("1. Multiply")
                .add("2. Divide")
                .add("3. Add")
                .add("4. Subtract")
                .add("Choose operation: Input a number: Input the 2nd number: 10.0 * 5.0 = 50.0").add("")
                .toString();
        assertThat(this.out.toString(), is(expected));
    }
}