package ru.job4j.io;

import org.junit.AfterClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
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
    private static final String DESTINATION = "unavailable.csv";
    private static final File DEST_FILE = new File(DESTINATION);

    @AfterClass
    public static void finalization() {
        DEST_FILE.deleteOnExit();
    }

    @Test
    public void unavailable() {
        String target = "unavailable.csv";
        new Analizy().unavailable("server.lg", target);
        try (BufferedReader reader = new BufferedReader(new FileReader(AnalizyTest.DESTINATION))) {
            List<String> expected = List.of("10:57:01;10:59:01", "11:01:02;11:02:02");
            assertThat(reader.lines().collect(Collectors.toList()), is(expected));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}