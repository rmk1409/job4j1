package ru.job4j.solid.oc;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.solid.sr.ConsoleInput;
import ru.job4j.solid.sr.InteractCalc;
import ru.job4j.solid.sr.MultiplyAct;

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
public class EngineerCalcTest {

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
    @Ignore //It works locally, but it doesn't work on the server side due to the delimiter(',' and '.')
    public void run() {
        Scanner scanner = new Scanner("1\n45\n-1");
        InteractCalc calc = new EngineerCalc(new ConsoleInput(scanner), List.of(new MultiplyAct()), List.of(new TanAct()));
        calc.start();
        String expected = new StringJoiner(System.lineSeparator())
                .add("Choose: ")
                .add("-1. Exit.")
                .add("0. Multiply operation.")
                .add("1. Tan operation.")
                .add("Input a number: Tan(45) = 1,00")
                .add("-----------------------------")
                .add("Choose: ")
                .add("-1. Exit.")
                .add("0. Multiply operation.")
                .add("1. Tan operation.")
                .add("-----------------------------")
                .add("")
                .toString();
        assertThat(this.out.toString(), is(expected));
    }
}