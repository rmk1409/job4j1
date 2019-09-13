package ru.job4j.io;

import org.junit.AfterClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TODO Description
 * Created by roman.pogorelov on 11.09.2019
 */
public class ChatTest {
    private static final String PHRASES = "phrases.txt";
    private static final String DESTINATION = "conversation.txt";
    private static final File DEST_FILE = new File(DESTINATION);

    @AfterClass
    public static void finalization() {
        DEST_FILE.deleteOnExit();
    }

    @Test
    public void exit() throws IOException {
        this.testChat("end", List.of("end"));
    }

    @Test
    public void stop() throws IOException {
        this.testChat("stop\none\ntwo\nthree\nend", List.of("stop", "one", "two", "three", "end"));
    }

    @Test
    public void phrases() throws IOException {
        Scanner scanner = new Scanner("a\nab\nabc\nend");
        List<String> phrases = new BufferedReader(
                new FileReader(
                        Chat.class.getClassLoader().getResource(ChatTest.PHRASES).getFile()))
                .lines()
                .collect(Collectors.toList());
        System.out.println(phrases);
        Chat chat = new Chat(ChatTest.PHRASES, ChatTest.DESTINATION, scanner);
        chat.run();
        long actual = Files.readAllLines(Paths.get(ChatTest.DESTINATION))
                .stream()
                .filter(phrases::contains)
                .count();
        assertThat(actual, is(3L));
    }

    private void testChat(String string, List expected) throws IOException {
        Scanner scanner = new Scanner(string);
        Chat chat = new Chat(ChatTest.PHRASES, ChatTest.DESTINATION, scanner);
        chat.run();
        List<String> actual = Files.readAllLines(Paths.get(ChatTest.DESTINATION));
        assertThat(actual, is(expected));
    }
}