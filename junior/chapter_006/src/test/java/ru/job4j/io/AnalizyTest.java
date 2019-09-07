package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 07.09.2019
 */
public class AnalizyTest {

    @Test
    public void unavailable() {
        String target = "unavailable.csv";
        new Analizy().unavailable("server.lg", target);
        try (BufferedReader reader = new BufferedReader(new FileReader("unavailable.csv"))) {
            List<String> expected = List.of("10:57:01;10:59:01", "11:01:02;11:02:02");
            assertThat(reader.lines().collect(Collectors.toList()), is(expected));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}