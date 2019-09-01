package ru.job4j.sorting;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by roman.pogorelov on 01.09.2019
 */
public class SortUserTest {

    @Test
    public void sort() {
        User user1 = new User(10, "1");
        User user2 = new User(5, "2");
        User user3 = new User(11, "3");
        Set<User> expected = new TreeSet<>();
        expected.add(user1);
        expected.add(user2);
        expected.add(user3);
        List<User> list = Arrays.asList(
                user1, user2, user3
        );
        Set<User> actual = new SortUser().sort(list);
        assertThat(expected, is(actual));
    }
}