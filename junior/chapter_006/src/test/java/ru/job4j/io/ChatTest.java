package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
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
    private String phrases = "phrases.txt";
    private String destination = "conversation.txt";

    @Test
    public void exit() throws IOException {
        this.testChat("end", List.of("end"));
    }

    @Test
    public void stop() throws IOException {
        this.testChat("stop\none\ntwo\nthree\nend", List.of("stop", "one", "two", "three", "end"));
    }

    private void testChat(String string, List expected) throws IOException {
        Scanner scanner = new Scanner(string);
        Chat chat = new Chat(this.phrases, this.destination, scanner);
        chat.run();
        List<String> actual = Files.readAllLines(Paths.get(this.destination));
        assertThat(actual, is(expected));
    }

    @Test
    public void phrases() throws IOException {
        Scanner scanner = new Scanner("a\nab\nabc\nend");
        List<String> phrases = new BufferedReader(
                new FileReader(
                        Chat.class.getClassLoader().getResource(this.phrases).getFile()))
                .lines()
                .collect(Collectors.toList());
        System.out.println(phrases);
        Chat chat = new Chat(this.phrases, this.destination, scanner);
        chat.run();
        long actual = Files.readAllLines(Paths.get(this.destination))
                .stream()
                .filter(phrases::contains)
                .count();
        assertThat(actual, is(3L));
    }
}