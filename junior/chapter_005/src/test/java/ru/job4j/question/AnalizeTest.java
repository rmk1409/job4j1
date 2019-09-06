package ru.job4j.question;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TODO Description
 * Created by roman.pogorelov on 06.09.2019
 */
public class AnalizeTest {
    @Test
    public void diff() {
        List<Analize.User> previous = List.of(
                new Analize.User(1, "Name 1"), new Analize.User(2, "Name 2"), new Analize.User(3, "Name 3"), new Analize.User(4, "Name 4"), new Analize.User(5, "Name 5"));
        List<Analize.User> current = List.of(new Analize.User(1, "Name 1"), new Analize.User(2, "new Name 2"), new Analize.User(3, "Name 3"), new Analize.User(6, "Name 6"), new Analize.User(7, "Name 7"));
        Analize.Info actual = new Analize.Info(2, 1, 2);
        assertThat(new Analize().diff(previous, current), is(actual));
    }

}